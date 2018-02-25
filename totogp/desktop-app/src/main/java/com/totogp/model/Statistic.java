package com.totogp.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "statistics", uniqueConstraints = @UniqueConstraint(columnNames = { "afterRace_id", "enrollment_id" }))
@NamedQuery(name = Statistic.GET_BY_ENROLLMENT, query = "select s from Statistic as s where s.enrollment = ?1")
@Cacheable(value = true)
public class Statistic {

  public static final String GET_BY_ENROLLMENT = "Statistic.GET_BY_USER";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Enrollment enrollment;

  @ManyToOne(fetch = FetchType.LAZY)
  private Race afterRace;

  @Column(name = "points_per_race", nullable = false)
  private Integer pointPerRace;

  @Column(name = "winner_winning_rate", nullable = false)
  private Integer winnerWinningCount;

  @Column(name = "pole_winnning_rate", nullable = false)
  private Integer poleWinningCount;

  @Column(name = "podium_first_winning_rate", nullable = false)
  private Integer podiumFirstWinningCount;

  @Column(name = "podium_second_winning_rate", nullable = false)
  private Integer podiumSecondWinningCount;

  @Column(name = "podium_third_winning_rate", nullable = false)
  private Integer podiumThirWinningCount;

  @Column(name = "points", nullable = false)
  private Integer points;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Statistic other = (Statistic) obj;
    if (afterRace == null) {
      if (other.afterRace != null) return false;
    } else if (!afterRace.equals(other.afterRace)) return false;
    if (enrollment == null) {
      if (other.enrollment != null) return false;
    } else if (!enrollment.equals(other.enrollment)) return false;
    return true;
  }

  public Race getAfterRace() {
    return afterRace;
  }

  public Enrollment getEnrollment() {
    return enrollment;
  }

  public Long getId() {
    return id;
  }

  public Integer getPodiumFirstWinningCount() {
    return podiumFirstWinningCount;
  }

  public Integer getPodiumSecondWinningCount() {
    return podiumSecondWinningCount;
  }

  public Integer getPodiumThirWinningCount() {
    return podiumThirWinningCount;
  }

  public Integer getPointPerRace() {
    return pointPerRace;
  }

  public Integer getPoints() {
    return points;
  }

  public Integer getPoleWinningCount() {
    return poleWinningCount;
  }

  public Integer getWinnerWinningCount() {
    return winnerWinningCount;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((afterRace == null) ? 0 : afterRace.hashCode());
    result = prime * result + ((enrollment == null) ? 0 : enrollment.hashCode());
    return result;
  }

  public void setAfterRace(final Race afterRace) {
    this.afterRace = afterRace;
  }

  public void setEnrollment(final Enrollment enrollment) {
    this.enrollment = enrollment;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setPodiumFirstWinningCount(final Integer podiumFirstWinningRate) {
    this.podiumFirstWinningCount = podiumFirstWinningRate;
  }

  public void setPodiumSecondWinningCount(final Integer podiumSecondWinningRate) {
    this.podiumSecondWinningCount = podiumSecondWinningRate;
  }

  public void setPodiumThirWinningCount(final Integer podiumThirWinningRate) {
    this.podiumThirWinningCount = podiumThirWinningRate;
  }

  public void setPointPerRace(final Integer pointPerRace) {
    this.pointPerRace = pointPerRace;
  }

  public void setPoints(final Integer points) {
    this.points = points;
  }

  public void setPoleWinningCount(final Integer poleWinningRate) {
    this.poleWinningCount = poleWinningRate;
  }

  public void setWinnerWinningCount(final Integer winnerWinningRate) {
    this.winnerWinningCount = winnerWinningRate;
  }

}
