package com.totogp.framework.persistence;

public interface GenericDAOI<T, PK> extends DAO<T, PK> {

  public void setEntityClass(final Class<T> entityClass);
}
