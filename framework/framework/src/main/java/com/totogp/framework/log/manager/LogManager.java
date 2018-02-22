package com.totogp.framework.log.manager;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.totogp.framework.annotation.Logged;

@SuppressWarnings("serial")
@Logged
@Interceptor
public class LogManager implements Serializable {

  public LogManager() {
    super();
  }

  @AroundInvoke
  public Object aroundInvoke(final InvocationContext context) throws Exception {

    final Logger targetLogger = LoggerFactory.getLogger(context.getTarget().getClass());
    final String prefix = context.getMethod().getName();

    long executionTime = 0;
    long startTime = 0;

    Object result = null;

    try {
      if (targetLogger.isInfoEnabled() && !targetLogger.isDebugEnabled()) {
        targetLogger.info(prefix, " - start");
      }

      if (targetLogger.isDebugEnabled()) {
        final String debugMessage = createDebugMessage(context, prefix);

        targetLogger.debug(debugMessage);

        startTime = System.currentTimeMillis();
      }

      result = context.proceed();
    } catch (final Exception e) {
      throw e;
    } finally {
      if (targetLogger.isDebugEnabled()) {
        executionTime = (System.currentTimeMillis() - startTime);

        targetLogger.debug(prefix + " - end : " + result + "(" + executionTime + "ms)");
      }
    }

    return result;
  }

  private String createDebugMessage(final InvocationContext context, final String prefix) {
    final StringBuffer debugMessage = new StringBuffer(prefix);
    debugMessage.append(" - start: {");

    for (int i = 0; i < context.getMethod().getParameterTypes().length; i++) {
      debugMessage.append("(");
      debugMessage.append(context.getMethod().getParameterTypes()[i]);
      debugMessage.append(") ");
      debugMessage.append(context.getParameters()[i]);

      if (i + 1 < context.getMethod().getParameterTypes().length) {
        debugMessage.append(", ");
      }
    }

    if (context.getMethod().getParameterTypes().length == 0) {
      debugMessage.append("none");
    }

    return debugMessage.append("}").toString();
  }
}
