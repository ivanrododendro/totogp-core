package com.totogp.framework.exception.manager;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.totogp.framework.annotation.PresentationExceptionManaged;
import com.totogp.framework.exception.BaseException;
import com.totogp.framework.exception.ExceptionManager;
import com.totogp.framework.presentation.JSFMessageUtils;

@SuppressWarnings("serial")
@PresentationExceptionManaged
@Interceptor
public class PresentationExceptionInterceptor extends ExceptionManager implements Serializable {

  @AroundInvoke
  public Object aroundInvoke(final InvocationContext context) throws Exception {
    Object result = null;

    try {
      result = context.proceed();
    } catch (final Exception e) {
      final Exception wrappedException = manageException(context, e);

      // si effectivement on n'a pas pu extraire une excpetion de base on la
      // propage
      if (wrappedException instanceof BaseException)
        JSFMessageUtils.addMessageFromException((BaseException) wrappedException, null, null);
      else
        throw wrappedException;
    }

    return result;
  }
}
