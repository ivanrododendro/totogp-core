package com.totogp.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.totogp.framework.persistence.DAO;
import com.totogp.model.Enrollment;
import com.totogp.model.Statistic;

@Stateless
public class HomeFacade {

  @Inject
  private DAO<Statistic, Long> statisticsDao;

  public List<Statistic> getUserStatistics(final Enrollment enrollment) {
    return statisticsDao.runQuery(Statistic.GET_BY_ENROLLMENT, enrollment);
  }
}
