package com.totogp.framework.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class BusinessException extends Exception implements BaseException {

  protected boolean logged = false;

  private final UUID uuid = UUID.randomUUID();

  private final List<Object> details = new ArrayList<Object>();

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public void addDetail(Serializable detail) {
    details.add(detail);
  }

  @Override
  public List<Object> getDetails() {
    return details;
  }

  @Override
  public String getUuid() {
    return uuid.toString();
  }

  @Override
  public boolean isLogged() {
    return logged;
  }

  @Override
  public void setLogged(boolean logged) {
    this.logged = logged;
  }
}
