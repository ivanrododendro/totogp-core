package com.totogp.framework.configuration;

import java.util.Map;

public interface ConfigurationManager {

  public Map<String, Object> getConfigurationMap();

  public Float getFloatValue(String propertyName);

  public Integer getIntegerValue(String propertyName);

  public String getStringValue(String propertyName);

  public void setFloatValue(String propertyName, String value);

  public void setIntegerValue(String propertyName, String value);

  public void setStringValue(String propertyName, String value);
}