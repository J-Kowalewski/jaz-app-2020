package pl.edu.pjwstk.jaz;

import java.util.Set;

public class User {
    private String username;
    private String password;
    Set<String> authorities;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public <T> User(Set<T> emptySet) {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public void addAuthority(String authority) {
        authorities.add(authority);
    }
}
