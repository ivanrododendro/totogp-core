package com.totogp.framework.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.ApplicationException;

@SuppressWarnings("serial")
@ApplicationException(rollback = true)
public class TechnicalException extends RuntimeException implements BaseException {

  protected boolean logged = false;

  private final UUID uuid = UUID.randomUUID();

  public TechnicalException() {
    super();
  }

  public TechnicalException(String message) {
    super(message);
  }

  public TechnicalException(String message, Throwable cause) {
    super(message, cause);
  }

  public TechnicalException(Throwable cause) {
    super(cause);
  }

  @Override
  public List<Object> getDetails() {
    final List<Object> details = new ArrayList<Object>();
    details.add(uuid);

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
