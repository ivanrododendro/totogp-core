
package com.totogp.producer;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import com.totogp.dao.UserDAO;
import com.totogp.model.User;

@SuppressWarnings("serial")
@SessionScoped
public class UserSessionProducer implements Serializable {

  @Inject
  private UserDAO userDAO;

  @Produces
  @ProducedUserSession
  public User produces() {
    final FacesContext facesContext = FacesContext.getCurrentInstance();
    final String currentUser = (String) facesContext.getExternalContext().getSessionMap().get("username");

    return userDAO.getByMail(currentUser);
  }
}
