package com.totogp.framework.annotation;

import javax.enterprise.util.AnnotationLiteral;

@SuppressWarnings("serial")
public class SpecificLiteral<T> extends AnnotationLiteral<SpecificDAO> implements SpecificDAO {

  private final Class<T> entityClass;

  public SpecificLiteral(Class<T> entityclass) {
    this.entityClass = entityclass;
  }

  @Override
  public Class<?> clazz() {
    return entityClass;
  }
}
