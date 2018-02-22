package com.totogp.framework.presentation;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import com.totogp.framework.exception.BaseException;
import com.totogp.framework.exception.BusinessException;
import com.totogp.framework.exception.TechnicalException;

public class JSFMessageUtils {

  public static final String NO_CLIENT_ID = null;

  public static final String NO_JSF_DETAILS = null;

  private static final String MESSAGES_BASENAME = "messages";

  private static final String TECHNINCAL_EXCEPTION_MESSAGE = "soclejee.technicalExceptionMessage";

  public static void addMessage(
      String messageId,
      Severity severity,
      String jsfDetail,
      String clientId,
      List<Object> details) {
    final FacesMessage message = getFacesMessage(messageId, details, severity, jsfDetail);

    FacesContext.getCurrentInstance().addMessage(clientId, message);
  }

  public static void addMessageFromException(BaseException exception, String detail, String clientId) {
    final FacesMessage message = getFacesMessage(exception, detail);

    FacesContext.getCurrentInstance().addMessage(clientId, message);
  }

  private static FacesMessage getFacesMessage(BaseException exception, String detail) {
    if (exception instanceof TechnicalException)
      return getFacesMessage((TechnicalException) exception, detail);
    else
      return getFacesMessage((BusinessException) exception, detail);
  }

  private static FacesMessage getFacesMessage(BusinessException exception, String detail) {
    final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
        getMessageText(exception.getMessage(), exception.getDetails()), detail);

    return facesMessage;
  }

  public static FacesMessage getFacesMessage(
      String messageId,
      List<Object> details,
      Severity severity,
      String jsfDetail) {
    return new FacesMessage(severity, getMessageText(messageId, details), jsfDetail);
  }

  private static FacesMessage getFacesMessage(TechnicalException exception, String detail) {

    final FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR,
        getMessageText(TECHNINCAL_EXCEPTION_MESSAGE, exception.getDetails()), detail);

    return facesMessage;
  }

  private static String getMessageText(String messageId, List<Object> details) {
    final ResourceBundle bundle = FacesContext.getCurrentInstance().getApplication()
        .getResourceBundle(FacesContext.getCurrentInstance(), MESSAGES_BASENAME);

    String message;

    try {
      message = bundle.getString(messageId);
    } catch (final Exception e) {
      message = messageId;
    }

    if (details == null) details = new ArrayList<Object>();

    return MessageFormat.format(message, details);
  }
}
