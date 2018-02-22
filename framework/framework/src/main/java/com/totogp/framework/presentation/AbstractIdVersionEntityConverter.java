package com.totogp.framework.presentation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang3.StringUtils;

public abstract class AbstractIdVersionEntityConverter<T> implements Converter {

  public static final String ID_PREFIX = "id";

  public static final String VERSION_PREFIX = "v";

  public String idFieldName;

  public String versionFieldName;

  public Class<T> entityClass;

  public AbstractIdVersionEntityConverter(final String idFieldName, final String versionFieldName) {
    super();

    this.idFieldName = idFieldName;
    this.versionFieldName = versionFieldName;
  }

  private void checkIdFieldNameDefined() {
    if (StringUtils.isEmpty(idFieldName))
      throw new ConverterException("le nom du champ corréspondant à l'identifiant n'a pas été défini");
  }

  @Override
  public Object getAsObject(final FacesContext facesContext, final UIComponent component, final String value) {
    try {
      if (StringUtils.isEmpty(value) || !value.startsWith(ID_PREFIX)) return value;

      checkIdFieldNameDefined();

      final Object entityResult = entityClass.newInstance();

      final String entityId = getIdFromValue(value);

      setFieldValue(entityResult, idFieldName, entityId);

      if (versionFieldName != null) {
        if (!value.contains(VERSION_PREFIX))
          throw new ConverterException("impossible de convertir la valeur " + value + " en Service");

        final String entityVersion = getVersionFromValue(value);

        setFieldValue(entityResult, versionFieldName, entityVersion);
      }

      return entityResult;
    } catch (final Exception e) {
      if (e instanceof ConverterException)
        throw (ConverterException) e;
      else
        throw new ConverterException(e);
    }
  }

  @Override
  public String getAsString(final FacesContext facesContext, final UIComponent component, final Object entity) {
    try {
      if (entity == null) return null;

      checkIdFieldNameDefined();

      final StringBuilder stringResultBuilder = new StringBuilder(ID_PREFIX + getFieldValue(entity, idFieldName));

      if (!StringUtils.isEmpty(versionFieldName)) {
        stringResultBuilder.append(VERSION_PREFIX).append(getFieldValue(entity, versionFieldName));
      }

      return stringResultBuilder.toString();
    } catch (final Exception e) {
      throw new ConverterException(e);
    }
  }

  public Integer getFieldValue(final Object object, final String fieldName)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    final String idFieldGetterMethodName = getGetterMethodName(fieldName);

    final Method idFieldGetterMethod = object.getClass().getMethod(idFieldGetterMethodName, new Class[0]);

    final Integer value = (Integer) idFieldGetterMethod.invoke(object, new Object[0]);

    return value;
  }

  private String getGetterMethodName(final String fieldName) {
    return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public String getIdFromValue(final String value) {
    return value.substring(ID_PREFIX.length(),
        value.indexOf(VERSION_PREFIX) == -1 ? value.length() : value.indexOf(VERSION_PREFIX));
  }

  private String getSetterNameMethod(final String fieldName) {
    return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
  }

  public String getVersionFromValue(final String value) {
    return value.substring(value.indexOf(VERSION_PREFIX) + 1);
  }

  public void setFieldValue(final Object result, final String fieldName, final String value)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
    final String setterMethodName = getSetterNameMethod(fieldName);

    final Method setterMethod = entityClass.getMethod(setterMethodName, Integer.class);

    if (setterMethod == null) throw new ConverterException("méthode " + setterMethod + " non trouvée");

    setterMethod.invoke(result, Integer.parseInt(value));
  }
}
