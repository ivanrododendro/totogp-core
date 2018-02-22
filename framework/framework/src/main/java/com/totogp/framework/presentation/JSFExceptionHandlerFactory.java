package com.totogp.framework.presentation;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class JSFExceptionHandlerFactory extends ExceptionHandlerFactory {

  private final javax.faces.context.ExceptionHandlerFactory parent;

  public JSFExceptionHandlerFactory(final ExceptionHandlerFactory parent) {
    this.parent = parent;
  }

  @Override
  public ExceptionHandler getExceptionHandler() {
    return new JSFExceptionHandler(this.parent.getExceptionHandler());
  }

}
