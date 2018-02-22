package com.totogp.model;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Cacheable(value = true)
@Table(name = "race_event_type", uniqueConstraints = @UniqueConstraint(columnNames = { "championship_id", "name" }) )
public class RaceEventType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER)
  private Championship championship;

  @Column(name = "name", nullable = false, length = 3)
  private String name;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final RaceEventType other = (RaceEventType) obj;
    if (championship == null) {
      if (other.championship != null) return false;
    } else if (!championship.equals(other.championship)) return false;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  public Championship getChampionship() {
    return championship;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((championship == null) ? 0 : championship.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public void setChampionship(final Championship championship) {
    this.championship = championship;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }
}
