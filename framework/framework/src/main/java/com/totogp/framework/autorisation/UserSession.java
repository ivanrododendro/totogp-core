package com.totogp.framework.autorisation;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class UserSession implements Serializable {

  private boolean initialised = false;

  private String username;

  private String nom;

  private String prenom;

  private List<String> roles;

  public String getNom() {
    return nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public List<String> getRoles() {
    return roles;
  }

  public String getUsername() {
    return username;
  }

  public boolean isInitialised() {
    return initialised;
  }

  public boolean isUserInRole(String role) {
    return roles.contains(role);
  }

  public void setInitialised(boolean initialised) {
    this.initialised = initialised;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
