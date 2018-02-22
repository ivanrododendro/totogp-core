package com.totogp.dao;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.totogp.framework.persistence.DAOAbstractImpl;
import com.totogp.model.User;

@SuppressWarnings({ "serial" })
@Dependent
public class UserDAO extends DAOAbstractImpl<User, Long> {

  private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

  private static final String SECURITY_KEY = "F3229A0B371ED2D9441B830D21A390C3";

  public User getByMail(final String email) {
    final Query query = em.createNamedQuery(com.totogp.model.User.GET_BY_EMAIL);

    return (User) query.setParameter("email", email).getSingleResult();
  }

  public User getByMailAndPassword(final String email, final String password) {
    try {
      // password = new String(Cypher.encrypt(password, SECURITY_KEY));

      final Query query = em.createNamedQuery(com.totogp.model.User.GET_BY_EMAIL_PASSWORD);

      return (User) query.setParameter("email", email).setParameter("password", password).getSingleResult();

    } catch (final Exception e) {
      logger.error("error while uthenticating user", e);
    }

    return null;
  }

  public User getByMailAndToken(final String email, final String toke) {
    final Query query = em.createNamedQuery(com.totogp.model.User.GET_BY_ACTIVATION_TOKEN);

    return (User) query.setParameter(1, email).setParameter(2, toke).getSingleResult();
  }

  @Override
  public User persist(final User user) {
    try {
      // user.password = new String(Cypher.encrypt(user.password,
      // SECURITY_KEY));

      em.persist(user);

      return user;
    } catch (final Exception e) {
      logger.error("error while creating user", e);
    }

    return null;
  }
}
