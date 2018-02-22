package com.totogp.dao;

import javax.enterprise.context.Dependent;
import javax.persistence.Query;

import com.totogp.framework.persistence.DAOAbstractImpl;
import com.totogp.model.Championship;
import com.totogp.model.Contest;
import com.totogp.model.Race;
import com.totogp.model.Regulation;

@SuppressWarnings("serial")
@Dependent
public class ContestDAO extends DAOAbstractImpl<Contest, Long> {

  public String getCurrentBetType(
      final Regulation regulation,
      final Integer year,
      final Race race,
      final Championship championship) {
    final Query query = em.createNamedQuery(Contest.GET_CURRENT_BET_TYPE);

    query.setParameter("regulationId", regulation.getId());
    query.setParameter("year", year);
    query.setParameter("raceId", race.getId());
    query.setParameter("championshipId", championship.getId());

    final Contest contest = (Contest) query.getSingleResult();

    return contest.getCurrentBetType();
  }
}
