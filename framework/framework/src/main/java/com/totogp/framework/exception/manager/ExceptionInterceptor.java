package com.totogp.framework.exception.manager;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import com.totogp.framework.annotation.ExceptionManaged;
import com.totogp.framework.exception.ExceptionManager;

@SuppressWarnings("serial")
@ExceptionManaged
@Interceptor
public class ExceptionInterceptor extends ExceptionManager implements Serializable {

  @AroundInvoke
  public Object aroundInvoke(final InvocationContext context) throws Exception {
    Object result = null;

    try {
      result = context.proceed();
    } catch (final Exception e) {
      final Exception wrappedException = manageException(context, e);

      if (wrappedException != null) throw wrappedException;
    }

    return result;
  }
}
