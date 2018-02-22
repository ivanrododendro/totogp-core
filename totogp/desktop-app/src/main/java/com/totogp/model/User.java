package com.totogp.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@SuppressWarnings("serial")
@Entity
@Table(name = "user")
@NamedQueries(value = {
    @NamedQuery(name = User.GET_BY_ACTIVATION_TOKEN, query = "select u from User u where u.email = :email and u.activationToken = :activationToken"),
    @NamedQuery(name = User.GET_BY_EMAIL_PASSWORD, query = " select u from User u where u.email = :email and u.password = :password"),
    @NamedQuery(name = User.GET_BY_EMAIL, query = " select u from User u where u.email = :email") })
public class User implements Serializable {

  public static final String GET_BY_ACTIVATION_TOKEN = "User.GET_BY_ACTIVATION_TOKEN";

  public static final String GET_BY_EMAIL_PASSWORD = "User.GET_BY_EMAIL_PASSWORD";

  public static final String GET_BY_EMAIL = "User.GET_BY_EMAIL";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
      + "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
  @Column(name = "email", unique = true, nullable = false, length = 100)
  private String email;

  @Column(name = "firstname", nullable = false, length = 40)
  private String firstname;

  @Column(name = "lastname", nullable = false, length = 40)
  private String lastname;

  @Column(name = "wants_to_be_notified", nullable = false)
  private Boolean wantsToBeNotified;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "rider_id")
  private Rider favoriteRider;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country_id")
  private Country country;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "user_id")
  private Set<Enrollment> enrollments;

  @Column(name = "active", nullable = false)
  private Boolean active = Boolean.FALSE;

  @Column(name = "activation_token", nullable = true)
  private String activationToken;

  @Column(name = "password", nullable = true)
  private String password;

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final User other = (User) obj;
    if (email == null) {
      if (other.email != null) return false;
    } else if (!email.equals(other.email)) return false;
    return true;
  }

  public String getActivationToken() {
    return activationToken;
  }

  public Boolean getActive() {
    return active;
  }

  public Country getCountry() {
    return country;
  }

  public String getEmail() {
    return email;
  }

  public Set<Enrollment> getEnrollments() {
    return enrollments;
  }

  public Rider getFavoriteRider() {
    return favoriteRider;
  }

  public String getFirstname() {
    return firstname;
  }

  public Long getId() {
    return id;
  }

  public String getLastname() {
    return lastname;
  }

  public String getPassword() {
    return password;
  }

  public Boolean getWantsToBeNotified() {
    return wantsToBeNotified;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    return result;
  }

  public void setActivationToken(final String activationToken) {
    this.activationToken = activationToken;
  }

  public void setActive(final Boolean active) {
    this.active = active;
  }

  public void setCountry(final Country country) {
    this.country = country;
  }

  public void setEmail(final String email) {
    this.email = email;
  }

  public void setEnrollments(final Set<Enrollment> enrollments) {
    this.enrollments = enrollments;
  }

  public void setFavoriteRider(final Rider favoriteRider) {
    this.favoriteRider = favoriteRider;
  }

  public void setFirstname(final String firstname) {
    this.firstname = firstname;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public void setLastname(final String lastname) {
    this.lastname = lastname;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setWantsToBeNotified(final Boolean wantsToBeNotified) {
    this.wantsToBeNotified = wantsToBeNotified;
  }
}
