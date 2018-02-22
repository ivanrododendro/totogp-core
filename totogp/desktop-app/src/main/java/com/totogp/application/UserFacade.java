package com.totogp.application;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.totogp.business.UserBusiness;
import com.totogp.framework.exception.BusinessException;
import com.totogp.model.Contest;
import com.totogp.model.User;

@Stateless
public class UserFacade {

  @EJB
  private UserBusiness userBusiness;

  public void activate(final String email, final String token) throws BusinessException {
    userBusiness.activate(email, token);
  }

  public void delete(final User user) {
    userBusiness.delete(user);
  }

  public void enroll(final User user, final Contest contest) {
    userBusiness.enroll(user, contest);
  }

  public User login(final String mailAdress, final String password) throws BusinessException {
    return userBusiness.login(mailAdress, password);
  }

  public User registerNew(final User newUser) {
    return userBusiness.registerNew(newUser);
  }

}
