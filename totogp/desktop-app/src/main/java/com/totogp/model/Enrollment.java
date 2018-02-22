package com.totogp.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = true)
@Table(name = "enrollment", uniqueConstraints = @UniqueConstraint(columnNames = { "contest_id", "user_id" }))
@NamedQuery(name = Enrollment.GET_BY_USER, query = "select e from Enrollment e where e.user = ?1")
public class Enrollment {

  public static final String GET_BY_USER = "Enrollment.GET_BY_USER";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @OneToOne(fetch = FetchType.EAGER)
  private Contest contest;

  @ManyToOne(fetch = FetchType.EAGER)
  private User user;

  @OneToMany(mappedBy = "enrollment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Set<Bet> bets;

  @OneToMany(mappedBy = "enrollment", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  private Set<Statistic> statistics;

  @Column(name = "points", nullable = true)
  private Float points;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Enrollment other = (Enrollment) obj;
    if (contest == null) {
      if (other.contest != null) return false;
    } else if (!contest.equals(other.contest)) return false;
    if (user == null) {
      if (other.user != null) return false;
    } else if (!user.equals(other.user)) return false;
    return true;
  }

  public Set<Bet> getBets() {
    return bets;
  }

  public Contest getContest() {
    return contest;
  }

  public int getId() {
    return id;
  }

  public Float getPoints() {
    return points;
  }

  public Set<Statistic> getStatistics() {
    return statistics;
  }

  public User getUser() {
    return user;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((contest == null) ? 0 : contest.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
  }

  public void setBets(final Set<Bet> bets) {
    this.bets = bets;
  }

  public void setContest(final Contest contest) {
    this.contest = contest;
  }

  public void setId(final int id) {
    this.id = id;
  }

  public void setPoints(final Float points) {
    this.points = points;
  }

  public void setStatistics(final Set<Statistic> statistics) {
    this.statistics = statistics;
  }

  public void setUser(final User user) {
    this.user = user;
  }

}
