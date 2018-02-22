package com.totogp.business;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.totogp.dao.UserDAO;
import com.totogp.framework.exception.BusinessException;
import com.totogp.model.Contest;
import com.totogp.model.User;
import com.totogp.util.MailService;

@Stateless
public class UserBusiness {

  @Inject
  private UserDAO userDao;

  @EJB
  private MailService mailService;

  public void activate(final String email, final String token) throws BusinessException {
    final User user = userDao.getByMailAndToken(email, token);

    if (user == null) throw new BusinessException("user.activation.failed");

    user.setActivationToken(token);
    user.setActive(Boolean.TRUE);
  }

  public void delete(final User user) {

  }

  public void enroll(final User user, final Contest contest) {

  }

  public User getByMailAdress(final String mailAdress) {
    return null;
  }

  public User login(final String mailAdress, final String password) throws BusinessException {
    final User user = userDao.getByMailAndPassword(mailAdress, password);

    if (user == null) throw new BusinessException("user.auth.failed");

    return user;
  }

  public void notifyAllForNextRaceBet() {

  }

  public User registerNew(final User newUser) {
    newUser.setActive(Boolean.FALSE);

    return newUser;
  }

  public void update(final User user) {

  }
}
