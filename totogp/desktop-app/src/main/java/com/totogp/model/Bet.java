package com.totogp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "bet", uniqueConstraints = @UniqueConstraint(columnNames = { "race_id", "enrollment_id", "type" }))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@NamedQueries(value = {
    @NamedQuery(name = Bet.GET_BY_CURRENT_BET, query = "select b from Bet b where b.enrollment = ?1 and b.type = ?2"),
    @NamedQuery(name = Bet.GET_UNCOMPUTED, query = "select b from Bet b where b.isWinning is null") })
public class Bet {
  public static final String GET_UNCOMPUTED = "Bet.GET_UNCOMPUTED";

  public static final String GET_BY_CURRENT_BET = "Bet.GET_BY_CURRENT_BET";

  public static final String PODIUM_BET = "podium_bet";

  public static final String POLE_BET = "pole_bet";

  public static final String WINNER_BET = "winner_bet";

  public static final String WINNER_BLIND_BET = "winner_blind_bet";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private Enrollment enrollment;

  @Column(name = "bet_date", nullable = false)
  private Date betDate;

  @Column(name = "type", nullable = false, insertable = false, updatable = false)
  private String type;

  @Column(name = "is_winning", nullable = true)
  private Boolean isWinning;

  @OneToOne(fetch = FetchType.LAZY)
  private Race race;

  @Column(name = "points", nullable = true)
  private Float points;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Bet other = (Bet) obj;
    if (enrollment == null) {
      if (other.enrollment != null) return false;
    } else if (!enrollment.equals(other.enrollment)) return false;
    if (race == null) {
      if (other.race != null) return false;
    } else if (!race.equals(other.race)) return false;
    return true;
  }

  public Date getBetDate() {
    return betDate;
  }

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public Long getId() {
    return id;
  }

  public Boolean getIsWinning() {
    return isWinning;
  }

  public Float getPoints() {
    return points;
  }

  public Race getRace() {
    return race;
  }

  public String getType() {
    return type;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((enrollment == null) ? 0 : enrollment.hashCode());
    result = prime * result + ((race == null) ? 0 : race.hashCode());
    return result;
  }

  public void setBetDate(final Date betDate) {
    this.betDate = betDate;
  }

  public void setEnrollment(final Enrollment enrollment) {
    this.enrollment = enrollment;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setIsWinning(final Boolean isWinning) {
    this.isWinning = isWinning;
  }

  public void setPoints(final Float points) {
    this.points = points;
  }

  public void setRace(final Race race) {
    this.race = race;
  }

  public void setType(final String type) {
    this.type = type;
  }
}
