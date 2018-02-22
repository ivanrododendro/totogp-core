package com.totogp.business;

import java.util.List;

import javax.ejb.Stateless;

import com.totogp.framework.persistence.DAO;
import com.totogp.model.Statistic;
import com.totogp.model.User;

@Stateless
public class StatsBusiness {
  private DAO<Statistic, Long> statisticDao;

  public List<Statistic> getUserStats(final User user) {
    return null;
  }
}
