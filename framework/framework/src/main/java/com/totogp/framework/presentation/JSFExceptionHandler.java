package com.totogp.framework.presentation;

import java.util.Iterator;

import javax.ejb.EJBException;
import javax.el.ELException;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import com.totogp.framework.exception.BaseException;
import com.totogp.framework.exception.ExceptionManager;

public class JSFExceptionHandler extends ExceptionHandlerWrapper {
  private final javax.faces.context.ExceptionHandler wrapped;

  private final ExceptionManager exceptionManager = new ExceptionManager();

  public JSFExceptionHandler(final ExceptionHandler exceptionHandler) {
    wrapped = exceptionHandler;
  }

  private Throwable getRootThrowable(Throwable throwable) {
    while ((throwable instanceof FacesException || throwable instanceof EJBException
        || throwable instanceof ELException) && throwable.getCause() != null) {
      throwable = throwable.getCause();
    }
    return throwable;
  }

  @Override
  public ExceptionHandler getWrapped() {
    return wrapped;
  }

  @Override
  public void handle() throws FacesException {
    for (final Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator(); it.hasNext();) {

      final ExceptionQueuedEventContext eventContext = it.next().getContext();
      final Throwable throwable = getRootThrowable(eventContext.getException());
      final FacesContext facesContext = eventContext.getContext();

      final BaseException wrappedException = exceptionManager.manageException(facesContext,
          throwable instanceof Exception ? (Exception) throwable : new Exception(throwable));

      JSFMessageUtils.addMessageFromException(wrappedException, JSFMessageUtils.NO_JSF_DETAILS,
          JSFMessageUtils.NO_CLIENT_ID);

      it.remove();
    }

    getWrapped().handle();
  }
}
