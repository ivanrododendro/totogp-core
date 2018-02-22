package com.totogp.framework.autorisation;

import java.util.List;

public interface RoleManager {
  public void disableLocalUser(String login);

  public List<String> getUserRoles(String username);

  public void updateLocalUser(String login, String defaultRole);
}
