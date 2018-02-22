package com.totogp.business;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import com.totogp.framework.persistence.DAO;
import com.totogp.model.Country;

@Stateless
@WebService
public class TestBusiness {

  @Inject
  private DAO<Country, Long> countryDao;

  public void createCountry(final String name) {
    final Country country = new Country();

    country.setName(name);

    countryDao.persist(country);
  }
}
