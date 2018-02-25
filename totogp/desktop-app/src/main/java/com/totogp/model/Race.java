package com.totogp.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = true)
@Table(name = "race", uniqueConstraints = @UniqueConstraint(columnNames = { "championship_id", "circuit_id", "date" }))
public class Race {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private Circuit circuit;

  @OneToOne(fetch = FetchType.LAZY)
  private Championship championship;

  @Column(name = "championship_rece_url", nullable = true, length = 255)
  private String championshipRaceUrl;

  @Column(name = "championship_rece_stats_url", nullable = true, length = 255)
  private String championshipRaceStatsUrl;

  @Column(name = "date", nullable = false)
  private Date date;

  @OneToMany(fetch = FetchType.LAZY)
  private Set<RaceEvent> receEvents;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Race other = (Race) obj;
    if (championship == null) {
      if (other.championship != null) return false;
    } else if (!championship.equals(other.championship)) return false;
    if (circuit == null) {
      if (other.circuit != null) return false;
    } else if (!circuit.equals(other.circuit)) return false;
    if (date == null) {
      if (other.date != null) return false;
    } else if (!date.equals(other.date)) return false;
    return true;
  }

  public Championship getChampionship() {
    return championship;
  }

  public String getChampionshipRaceStatsUrl() {
    return championshipRaceStatsUrl;
  }

  public String getChampionshipRaceUrl() {
    return championshipRaceUrl;
  }

  public Circuit getCircuit() {
    return circuit;
  }

  public Date getDate() {
    return date;
  }

  public Long getId() {
    return id;
  }

  public Set<RaceEvent> getReceEvents() {
    return receEvents;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((championship == null) ? 0 : championship.hashCode());
    result = prime * result + ((circuit == null) ? 0 : circuit.hashCode());
    result = prime * result + ((date == null) ? 0 : date.hashCode());
    return result;
  }

  public void setChampionship(final Championship championship) {
    this.championship = championship;
  }

  public void setChampionshipRaceStatsUrl(final String championshipRaceStatsUrl) {
    this.championshipRaceStatsUrl = championshipRaceStatsUrl;
  }

  public void setChampionshipRaceUrl(final String championshipRaceUrl) {
    this.championshipRaceUrl = championshipRaceUrl;
  }

  public void setCircuit(final Circuit circuit) {
    this.circuit = circuit;
  }

  public void setDate(final Date date) {
    this.date = date;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setReceEvents(final Set<RaceEvent> receEvents) {
    this.receEvents = receEvents;
  }
}
