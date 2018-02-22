package com.totogp.framework.presentation;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class JSFNoCachePhaseListener implements PhaseListener {
  @Override
  public void afterPhase(PhaseEvent arg0) {
    // does nothing
  }

  @Override
  public void beforePhase(PhaseEvent event) {
    final FacesContext facesContext = event.getFacesContext();
    final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
    response.addHeader("Pragma", "no-cache");
    response.addHeader("Cache-Control", "no-cache");
    response.addHeader("Cache-Control", "no-store");
    response.addHeader("Cache-Control", "must-revalidate");
    response.addHeader("Expires", "0");
  }

  @Override
  public PhaseId getPhaseId() {
    return PhaseId.RENDER_RESPONSE;
  }
}