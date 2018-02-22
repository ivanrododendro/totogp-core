package com.totogp.framework.exception;

import javax.faces.context.FacesContext;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionManager {

  public static final boolean DONT_WRAP_UNKNOWN_NOT_RUNTIME = false;

  public static final boolean WRAP_UNKNOWN_NOT_RUNTIME = true;

  protected static final Logger thisLogger = LoggerFactory.getLogger(ExceptionManager.class);

  protected Exception getWrappedException(final Exception e, final boolean wrapUnknownNotRuntime) {
    Exception exceptionToThrow = null;

    if (e instanceof BaseException) {
      exceptionToThrow = e;
    } else if (e instanceof RuntimeException) {
      exceptionToThrow = new TechnicalException(e);
    } else if (!wrapUnknownNotRuntime) {
      exceptionToThrow = e;
    } else {
      exceptionToThrow = new TechnicalException(e);
    }

    return exceptionToThrow;
  }

  protected void logException(final String contextSuffix, final Exception e, final Logger logger) {

    if (e instanceof TechnicalException) {
      final TechnicalException socleException = (TechnicalException) e;

      if (socleException.isLogged()) return;

      logger.error("an exception (" + socleException.getUuid() + ") occured " + contextSuffix,
          socleException.getCause() == null ? socleException : socleException.getCause());

      socleException.setLogged(true);
    } else if (e instanceof BusinessException) {
      final BusinessException socleException = (BusinessException) e;

      if (socleException.isLogged()) return;

      logger.debug("an application error occured : " + socleException.getMessage() + " (" + socleException.getUuid()
          + ") " + contextSuffix);

      socleException.setLogged(true);

    } else {
      // this can happen only if the exception has been wrapped using
      // DONT_WRAP_UNKNOWN_NOT_RUNTIME, which is the case for services (non UI)
      // exception management

      thisLogger.warn("a non runtime exception not extending SocleBusinessException was detected : "
          + e.getClass().getCanonicalName() + contextSuffix + ", please correct");

      logger.error("an exception occured " + contextSuffix);
    }
  }

  public BaseException manageException(final FacesContext facesContext, final Exception e) {
    final BaseException exceptionToThrow = (BaseException) getWrappedException(e, WRAP_UNKNOWN_NOT_RUNTIME);
    final String contextSuffix = " in view " + facesContext.getViewRoot().getViewId();

    logException(contextSuffix, (Exception) exceptionToThrow, thisLogger);

    return exceptionToThrow;
  }

  public Exception manageException(final InvocationContext invocationContext, final Exception e) {
    final Exception exceptionToThrow = getWrappedException(e, DONT_WRAP_UNKNOWN_NOT_RUNTIME);
    final String contextSuffix = "while executing method ".concat(invocationContext.getMethod().getName());
    final Logger targetLogger = LoggerFactory.getLogger(invocationContext.getTarget().getClass());

    logException(contextSuffix, exceptionToThrow, targetLogger);

    return exceptionToThrow;
  }
}
