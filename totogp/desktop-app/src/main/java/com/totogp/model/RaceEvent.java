package com.totogp.model;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = true)
@Table(name = "race_event", uniqueConstraints = @UniqueConstraint(columnNames = { "race_id", "type_id" }) )
public class RaceEvent {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  private RaceEventType type;

  @Column(name = "date", nullable = false)
  private Date date;

  @ManyToOne(fetch = FetchType.LAZY)
  private Race race;

  public Date getDate() {
    return date;
  }

  public Long getId() {
    return id;
  }

  public Race getRace() {
    return race;
  }

  public RaceEventType getType() {
    return type;
  }

  public void setDate(final Date date) {
    this.date = date;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setRace(final Race race) {
    this.race = race;
  }

  public void setType(final RaceEventType type) {
    this.type = type;
  }
}
