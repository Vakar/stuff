package space.vakar.stuff.persistence.model;

import space.vakar.stuff.persistence.api.DomainEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "APP_USER")
public class User implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String email;
  private String pswd;

  public User() {}

  public User(String username, String email, String pswd) {
    this.username = username;
    this.email = email;
    this.pswd = pswd;
  }

  public User(int id, String username, String email, String pswd) {
    this(username, email, pswd);
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPswd() {
    return pswd;
  }

  public void setPswd(String pswd) {
    this.pswd = pswd;
  }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pswd, user.pswd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, pswd);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", email='" + email + '\'' +
                ", pswd='" + pswd + '\'' +
                '}';
    }
}
