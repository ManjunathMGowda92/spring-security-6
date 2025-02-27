package org.fourstack.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Document(collection = "app_users")
public class AppUserDetails implements UserDetails {
  private String username;
  private String password;
  private Set<GrantedAuthority> authorities;

  private boolean isAccountNonExpired;

  private boolean isAccountNonLocked;

  private boolean isCredentialsNonExpired;

  private boolean isEnabled;

  public AppUserDetails() {
  }

  public AppUserDetails(String username, String password) {
    this(username, password, Collections.emptySet(), true, true, true, true);
  }

  public AppUserDetails(String username, String password, Set<GrantedAuthority> authorities) {
    this(username, password, authorities != null ? authorities : Collections.emptySet(), true, true, true, true);
  }

  public AppUserDetails(String username, String password, Set<GrantedAuthority> authorities, boolean isAccountNonExpired,
                        boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled) {
    this.username = username;
    this.password = password;
    this.authorities = authorities;
    this.isAccountNonExpired = isAccountNonExpired;
    this.isAccountNonLocked = isAccountNonLocked;
    this.isCredentialsNonExpired = isCredentialsNonExpired;
    this.isEnabled = isEnabled;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return isAccountNonExpired;
  }

  @Override
  public boolean isAccountNonLocked() {
    return isAccountNonLocked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    isAccountNonExpired = accountNonExpired;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    isAccountNonLocked = accountNonLocked;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    isCredentialsNonExpired = credentialsNonExpired;
  }

  public void setEnabled(boolean enabled) {
    isEnabled = enabled;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAuthorities(Set<GrantedAuthority> authorities) {
    this.authorities = authorities;
  }
}
