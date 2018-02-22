package com.totogp.framework.exception;

import java.util.List;

public interface BaseException {

  public List<Object> getDetails();

  public String getUuid();

  public boolean isLogged();

  public void setLogged(boolean logged);
}
