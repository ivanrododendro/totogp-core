package com.totogp.producer;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.totogp.framework.persistence.DAO;
import com.totogp.model.Enrollment;
import com.totogp.model.User;

@SessionScoped
public class CurrentEnrollmentProducer implements Serializable {

  @Inject
  private DAO<Enrollment, Long> enrollmentDao;

  @Inject
  @ProducedUserSession
  private User user;

  @Produces
  @ProducedCurrentEnrollment
  public Enrollment produces() {
    // TODO articuler
    return enrollmentDao.runQuery(Enrollment.GET_BY_USER, user).get(0);
  }
}
