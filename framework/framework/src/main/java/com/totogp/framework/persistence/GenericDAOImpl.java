package com.totogp.framework.persistence;

import javax.enterprise.context.Dependent;
import javax.inject.Named;

import com.totogp.framework.annotation.ExceptionManaged;
import com.totogp.framework.annotation.GenericDAO;
import com.totogp.framework.annotation.Logged;

@SuppressWarnings("serial")
@Named
@Dependent
@GenericDAO
@Logged
@ExceptionManaged
public class GenericDAOImpl<T, PK> extends DAOAbstractImpl<T, PK> implements GenericDAOI<T, PK> {

  public GenericDAOImpl() {
    super();
  }

  @Override
  public void setEntityClass(final Class<T> entityClass) {
    this.entityClass = entityClass;
  }
}
