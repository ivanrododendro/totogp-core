package com.totogp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "results", uniqueConstraints = @UniqueConstraint(columnNames = { "race_id", "rider_id", "type" }))
@NamedQuery(name = Result.GET_BY_RACE_TYPE, query = "select r from Result r where r.race = ?1 and r.type = ?2")
public class Result {

  public final static String GET_BY_RACE_TYPE = "Result.GET_BY_RACE_TYPE";

  @Id
  @GeneratedValue
  private long id;

  @OneToOne
  private Race race;

  @OneToOne
  private Rider rider;

  private ResultType type;

  public long getId() {
    return id;
  }

  public Race getRace() {
    return race;
  }

  public Rider getRider() {
    return rider;
  }

  public ResultType getType() {
    return type;
  }

  public void setId(final long id) {
    this.id = id;
  }

  public void setRace(final Race race) {
    this.race = race;
  }

  public void setRider(final Rider rider) {
    this.rider = rider;
  }

  public void setType(final ResultType type) {
    this.type = type;
  }
}
