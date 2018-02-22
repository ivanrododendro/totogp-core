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

@Entity
@Table(name = "circuit")
@Cacheable(value = true)
public class Circuit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", length = 255, nullable = false, unique = true)
  private String name;

  @OneToOne(fetch = FetchType.EAGER)
  private Country country;

  @Column(name = "wikipedia_url", length = 255, nullable = true)
  private String wikipediaUrl;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Circuit other = (Circuit) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  public Country getCountry() {
    return country;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getWikipediaUrl() {
    return wikipediaUrl;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setWikipediaUrl(final String wikipediaUrl) {
    this.wikipediaUrl = wikipediaUrl;
  }

}
