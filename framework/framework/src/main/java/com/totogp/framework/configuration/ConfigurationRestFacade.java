package com.totogp.framework.configuration;

import java.util.Map;

public abstract class ConfigurationRestFacade {

  protected ConfigurationManager configurationManager;

  protected Map<String, Object> getProperties() {
    return configurationManager.getConfigurationMap();
  }

  protected String getPropertyValue(String propertyName, String newValue) {

    if (newValue != null && !newValue.equals("")) configurationManager.setStringValue(propertyName, newValue);

    return configurationManager.getConfigurationMap().get(propertyName).toString();
  }

  protected abstract void setConfigurationManager(ConfigurationManager configurationManager);
}
