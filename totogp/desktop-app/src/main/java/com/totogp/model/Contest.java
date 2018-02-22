package com.totogp.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "contest", uniqueConstraints = @UniqueConstraint(columnNames = { "championship_id", "regulation_id" }))
@NamedQuery(name = Contest.GET_CURRENT_BET_TYPE, query = "select c from Contest c where c.regulation.id = :regulationId and c.currentRace.id = :raceId and c.open = true and c.year = :year and c.championship.id = :championshipId")
@Cacheable(value = true)
public class Contest {

  public static final String GET_CURRENT_BET_TYPE = "Contest.GET_CUURENT_BET_TYPE";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  private Championship championship;

  @OneToOne(fetch = FetchType.EAGER)
  private Regulation regulation;

  @Column(name = "year", nullable = false)
  private Integer year;

  @OneToOne(fetch = FetchType.EAGER)
  private Race currentRace;

  @Column(name = "current_bet_type", nullable = false)
  private String currentBetType;

  @Column(name = "open", nullable = false)
  private Boolean open;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Contest other = (Contest) obj;
    if (championship == null) {
      if (other.championship != null) return false;
    } else if (!championship.equals(other.championship)) return false;
    if (regulation == null) {
      if (other.regulation != null) return false;
    } else if (!regulation.equals(other.regulation)) return false;
    return true;
  }

  public Championship getChampionship() {
    return championship;
  }

  public String getCurrentBetType() {
    return currentBetType;
  }

  public Race getCurrentRace() {
    return currentRace;
  }

  public Long getId() {
    return id;
  }

  public Boolean getOpen() {
    return open;
  }

  public Regulation getRegulation() {
    return regulation;
  }

  public Integer getYear() {
    return year;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((championship == null) ? 0 : championship.hashCode());
    result = prime * result + ((regulation == null) ? 0 : regulation.hashCode());
    return result;
  }

  public void setChampionship(final Championship championship) {
    this.championship = championship;
  }

  public void setCurrentBetType(final String currentBetType) {
    this.currentBetType = currentBetType;
  }

  public void setCurrentRace(final Race currentRace) {
    this.currentRace = currentRace;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setOpen(final Boolean open) {
    this.open = open;
  }

  public void setRegulation(final Regulation regulation) {
    this.regulation = regulation;
  }

  public void setYear(final Integer year) {
    this.year = year;
  }

}
