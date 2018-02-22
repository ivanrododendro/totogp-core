package com.totogp.model;

import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "championship")
@Cacheable(value = true)
public class Championship {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 30, unique = true)
  private String name;

  @Column(name = "homepage", nullable = true, length = 255)
  private String homepage;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "championship")
  private Set<Race> races;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Championship other = (Championship) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  public String getHomepage() {
    return homepage;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Set<Race> getRaces() {
    return races;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public void setHomepage(final String homepage) {
    this.homepage = homepage;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setRaces(final Set<Race> races) {
    this.races = races;
  }
}
