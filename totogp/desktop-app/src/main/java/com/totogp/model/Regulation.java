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
@Cacheable(value = true)
@Table(name = "regulation")
public class Regulation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name", nullable = false, length = 255)
  private String name;

  @OneToOne(fetch = FetchType.EAGER)
  private Country registrationCountry;

  @Column(name = "public_url", nullable = true, length = 255)
  private String publicUrl;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final Regulation other = (Regulation) obj;
    if (name == null) {
      if (other.name != null) return false;
    } else if (!name.equals(other.name)) return false;
    return true;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getPublicUrl() {
    return publicUrl;
  }

  public Country getRegistrationCountry() {
    return registrationCountry;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setName(final String name) {
    this.name = name;
  }

  public void setPublicUrl(final String publicUrl) {
    this.publicUrl = publicUrl;
  }

  public void setRegistrationCountry(final Country registrationCountry) {
    this.registrationCountry = registrationCountry;
  }
}
