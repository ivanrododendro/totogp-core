package com.totogp.framework.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ejb.SessionContext;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.totogp.framework.configuration.model.generated.ConfigurationType;
import com.totogp.framework.configuration.model.generated.FileDefinitionType;
import com.totogp.framework.configuration.model.generated.PropertyDefinitionType;
import com.totogp.framework.exception.TechnicalException;

public abstract class ConfigurationManagerImpl implements ConfigurationManager {
  private static final String TIMER_ID = "CONFIGURATION_FILE_REFRESH_TIMER";

  private static final String CONFIGURATION_AUTO_REFRESH_INTERVAL = "configuration.autoRefreshInterval";

  private static final Integer DEFAULT_AUTO_REFRESH_INTERVAL = 30000;

  private final Logger logger = LoggerFactory.getLogger(ConfigurationManagerImpl.class);

  private ConfigurationType configurationDef;

  private Integer autoRefreshInterval;

  private long lastTimeSync;

  private Map<String, Object> propertyMap = new HashMap<String, Object>();

  private boolean initialised = false;

  protected SessionContext context;

  private String configurationFilename;

  protected String configurationDefFilename;

  private boolean externalConfiguration = false;

  private Object getActualItemValue(PropertyDefinitionType propertyDef, String value) throws IOException {
    Object result = null;

    final String name = propertyDef.getName();
    final String javaType = propertyDef.getJavaType().value();

    if (StringUtils.isEmpty(value)) value = propertyDef.getDefaultValue();

    if (StringUtils.isEmpty(value)) throw new TechnicalException("la propriété " + name + " doit être renseignée");

    if (javaType.equals(String.class.getName()))
      result = value;
    else if (javaType.equals(Integer.class.getName()))
      result = Integer.parseInt(value);
    else if (javaType.equals(Float.class.getName()))
      result = Float.parseFloat(value);
    else if (javaType.equals(File.class.getName())) {
      if (!value.endsWith(File.separator)) value += File.separator;

      final FileDefinitionType filePropertyDef = (FileDefinitionType) propertyDef;

      final File f = new File(value);

      if (!f.exists() && filePropertyDef.isMustExists())
        throw new TechnicalException("le fichier " + value + " n'existe pas");

      if ((f.isDirectory() != filePropertyDef.isIsDirectory()) && f.exists())
        throw new TechnicalException(name + " la nature repertoire/fichier est incorrecte");

      if (!f.exists() && filePropertyDef.isIsDirectory() && filePropertyDef.isCreateIfNotExists()) f.mkdir();

      result = value;
    }

    return result;
  }

  private InputStream getConfigurationFileInputStream() throws FileNotFoundException {
    InputStream inStream;

    if (externalConfiguration)
      inStream = new FileInputStream(new File(configurationFilename));
    else
      inStream = getClass().getResourceAsStream("/" + configurationFilename);

    return inStream;
  }

  @Override
  public Map<String, Object> getConfigurationMap() {
    // FIXME clone it before sending it
    return propertyMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see fr.vdm.soclejee.configuration.ConfigurationManager#getFloatValue(java.
   * lang .String, java.lang.Float)
   */
  @Override
  public Float getFloatValue(String propertyName) {
    return (Float) propertyMap.get(propertyName);
  }

  @Override
  public Integer getIntegerValue(String propertyName) {
    return (Integer) propertyMap.get(propertyName);
  }

  private PropertyDefinitionType getPropertyDefinition(String propertyName) {
    for (final PropertyDefinitionType tmp : configurationDef.getPropertyDefinition())
      if (tmp.getName().equals(propertyName)) return tmp;

    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.vdm.soclejee.configuration.ConfigurationManager#getStringValue(java.
   * lang.String, java.lang.String)
   */
  @Override
  public String getStringValue(String propertyName) {
    return (String) propertyMap.get(propertyName);
  }

  protected abstract void initFilenames();

  public boolean isConfigurationExternal() {
    return externalConfiguration;
  }

  private void loadCondfigurationDef() {
    InputStream confDefInputStream = null;

    try {
      if (StringUtils.isEmpty(configurationDefFilename)) {
        logger.info("attribut configurationDefFilename n'est pas positionné, gestion de configuration non activée");
        return;
      }

      if (!configurationDefFilename.startsWith("/")) configurationDefFilename = "/" + configurationDefFilename;

      final String implicitConfigurationFileName = configurationDefFilename.substring(0,
          configurationDefFilename.toLowerCase().indexOf(".xml")) + ".properties";

      confDefInputStream = getClass().getResourceAsStream(configurationDefFilename);

      if (confDefInputStream == null) throw new TechnicalException("le fichier :" + configurationDefFilename
          + " n'existe pas à la racine du classpath, gestion de configuration non disponible");

      configurationDef = parseConfigurationDefinition(confDefInputStream);

      configurationFilename = configurationDef.getConfigurationFileName();

      if (StringUtils.isEmpty(configurationFilename)) {
        configurationFilename = implicitConfigurationFileName;

        if (getClass().getResourceAsStream(configurationFilename) == null) throw new TechnicalException(
            "le fichier :" + configurationFilename + " n'existe pas" + ", gestion de configuration non disponible");
      } else {
        externalConfiguration = true;

        if (getClass().getResourceAsStream(configurationFilename) != null) logger.warn("un fichier "
            + implicitConfigurationFileName
            + "est présent dans le war alors qu'un fichier de confiugration externe à été indiqué. Le fichier externe a été retenu.");
      }

      logger.info("configuration externe et rechargeable à chaud : " + externalConfiguration);
    } catch (final Exception e) {
      throw new TechnicalException(
          "erreur pendant le chargement du fichier de definition de la configuration " + configurationDefFilename, e);
    } finally {
      try {
        confDefInputStream.close();
      } catch (final Exception e) {
        logger.error(e.getMessage(), e);
      }
    }
  }

  private void loadConfiguration(boolean firstTimeLoad) {
    InputStream inStream = null;
    final Map<String, Object> tmpPropertyMap = new HashMap<String, Object>();

    logger.info("chargement du fichier de configuration : " + configurationFilename);

    try {
      inStream = getConfigurationFileInputStream();

      final Properties properties = new Properties();
      properties.load(inStream);

      for (final PropertyDefinitionType propertyDefinition : configurationDef.getPropertyDefinition()) {
        final String name = propertyDefinition.getName();
        final String value = properties.getProperty(name);
        final Object actualValue = getActualItemValue(propertyDefinition, value);

        tmpPropertyMap.put(name, actualValue);
      }

      propertyMap = tmpPropertyMap;

      if (propertyMap.get(CONFIGURATION_AUTO_REFRESH_INTERVAL) != null)
        autoRefreshInterval = Integer.parseInt((String) propertyMap.get(CONFIGURATION_AUTO_REFRESH_INTERVAL));

      updateLastTimeSync();
    } catch (final Exception e) {
      if (firstTimeLoad)
        logger.error("la configuration n'a pas été modifiée", e);
      else
        logger.error("la configuration n'a pas été chargée", e);
    } finally {
      try {
        if (inStream != null) inStream.close();
      } catch (final IOException e) {
        logger.error(e.getMessage(), e);
      }
    }
  }

  private void logConfiguration() {
    for (final PropertyDefinitionType propertyDefinition : configurationDef.getPropertyDefinition()) {
      final String name = propertyDefinition.getName();

      logger.info(name + " : " + (propertyDefinition.isHidden() ? propertyMap.get(name).toString().replaceAll(".", "*")
          : propertyMap.get(name)));
    }
  }

  @SuppressWarnings("unchecked")
  private ConfigurationType parseConfigurationDefinition(InputStream confDefInputStream) throws JAXBException {
    final String packageName = ConfigurationType.class.getPackage().getName();
    final JAXBContext jaxbContext = JAXBContext.newInstance(packageName);
    final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

    final ConfigurationType configurationDef = ((JAXBElement<ConfigurationType>) unmarshaller
        .unmarshal(confDefInputStream)).getValue();

    return configurationDef;
  }

  public void postConstruct() {
    initFilenames();

    loadCondfigurationDef();

    synchronized (this) {
      loadConfiguration(true);
    }

    logConfiguration();

    updateAutoRefreshInterval((Integer) propertyMap.get(CONFIGURATION_AUTO_REFRESH_INTERVAL));

    initialised = true;
  }

  private void saveConfiguration() {
    synchronized (this) {
      FileOutputStream fileOutputStream = null;
      final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

      try {
        fileOutputStream = new FileOutputStream(configurationFilename);

        final Properties properties = new Properties();

        for (final String key : propertyMap.keySet())
          properties.put(key, propertyMap.get(key).toString());

        properties.store(fileOutputStream,
            "mise à jour par " + this.getClass() + ", le " + dateFormat.format(new Date()));

        updateLastTimeSync();

        logConfiguration();

        updateAutoRefreshInterval((Integer) propertyMap.get(CONFIGURATION_AUTO_REFRESH_INTERVAL));
      } catch (final Exception e) {
        throw new TechnicalException("Erreur pendant l'écriture du fichier de configuration : " + configurationFilename,
            e);
      } finally {
        if (fileOutputStream != null) try {
          fileOutputStream.close();
        } catch (final IOException e) {
          logger.error("", e);
        }
      }
    }
  }

  protected abstract void setContext(SessionContext context);

  /*
   * (non-Javadoc)
   * 
   * @see fr.vdm.soclejee.configuration.ConfigurationManager#setFloatValue(java.
   * lang .String, java.lang.String)
   */
  @Override
  public void setFloatValue(String propertyName, String value) {
    setObjectValue(propertyName, value);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.vdm.soclejee.configuration.ConfigurationManager#setIntegerValue(java
   * .lang.String, java.lang.String)
   */
  @Override
  public void setIntegerValue(String propertyName, String value) {
    setObjectValue(propertyName, value);
  }

  private void setObjectValue(String propertyName, String value) {
    synchronized (this) {
      try {
        final PropertyDefinitionType propertyDef = getPropertyDefinition(propertyName);

        if (propertyDef == null) throw new TechnicalException("la propriété " + propertyName
            + "n'existe pas dans le fichier de configuration : " + configurationFilename);

        loadConfiguration(false);

        final Object valueObject = getActualItemValue(propertyDef, value);

        propertyMap.remove(propertyName);

        propertyMap.put(propertyName, valueObject);

        saveConfiguration();

        logConfiguration();
      } catch (final Exception e) {
        throw new TechnicalException(e);
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * fr.vdm.soclejee.configuration.ConfigurationManager#setStringValue(java.
   * lang.String, java.lang.String)
   */
  @Override
  public void setStringValue(String propertyName, String value) {
    setObjectValue(propertyName, value);
  }

  @Timeout
  public void timeout(Timer timer) {
    if (!externalConfiguration) return;

    synchronized (this) {
      final File configurationFile = new File(configurationFilename);

      if (configurationFile.lastModified() != lastTimeSync) {

        logger.info("Le fichier de configuration : " + configurationFilename + " a été modifié");

        loadConfiguration(false);

        logConfiguration();
      }
    }
  }

  private void updateAutoRefreshInterval(Integer autoRefreshInterval) {
    if (!externalConfiguration) {
      if (autoRefreshInterval != null) logger.warn(
          "un intervalle de rafraîchissement automatique a été spécifié alors que la configuration est embarquée dans le WAR et ne peut pas être modifiée, aucun rafraîchissement sera effectué");

      return;
    }

    if (autoRefreshInterval == null || autoRefreshInterval < 0) autoRefreshInterval = DEFAULT_AUTO_REFRESH_INTERVAL;

    if (autoRefreshInterval.equals(this.autoRefreshInterval)) return;

    this.autoRefreshInterval = autoRefreshInterval;

    logger.debug("autoRefreshInterval : " + autoRefreshInterval);

    for (final Object timer : context.getTimerService().getTimers())
      if (((Timer) timer).getInfo().equals(TIMER_ID)) {
        ((Timer) timer).cancel();

        continue;
      }

    context.getTimerService().createTimer(new Date(), this.autoRefreshInterval, TIMER_ID);
  }

  private void updateLastTimeSync() {
    lastTimeSync = new File(configurationFilename).lastModified();

    logger.debug("lastModifiedConfiguration : " + lastTimeSync);
  }
}
