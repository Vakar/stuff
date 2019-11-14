package space.vakar.stuff.persistence.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import space.vakar.stuff.persistence.api.DomainEntity;

@Entity
@Table(name = "APP_USER")
public class User implements DomainEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String username;
  private String email;
  private String pswd;

  /** Default constructor. */
  public User() {}

  /**
   * {@link User} parametrised constructor.
   *
   * @param username {@link User} name
   * @param email {@link User} email
   * @param pswd {@link User} password
   */
  public User(String username, String email, String pswd) {
    this.username = username;
    this.email = email;
    this.pswd = pswd;
  }

  /**
   * {@link User} parametrised constructor.
   *
   * @param id {@link User} entity id
   * @param username {@link User} name
   * @param email {@link User} email
   * @param pswd {@link User} password
   */
  public User(int id, String username, String email, String pswd) {
    this(username, email, pswd);
    this.id = id;
  }

  /**
   * Get {@link User} entity id.
   *
   * @return {@link User} entity id
   */
  public int getId() {
    return id;
  }

  /**
   * Set {@link User} entity id.
   *
   * @param id {@link User} entity id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Get {@link User} name.
   *
   * @return {@link User} name
   */
  public String getUsername() {
    return username;
  }

  /**
   * Set {@link User} name.
   *
   * @param username {@link User} name
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * Get {@link User} email.
   *
   * @return {@link User} email
   */
  public String getEmail() {
    return email;
  }

  /**
   * Set {@link User} email.
   *
   * @param email {@link User} email
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Get {@link User} password.
   *
   * @return {@link User} password
   */
  public String getPswd() {
    return pswd;
  }

  /**
   * Set {@link User} password.
   *
   * @param pswd {@link User} password
   */
  public void setPswd(String pswd) {
    this.pswd = pswd;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id == user.id
        && Objects.equals(username, user.username)
        && Objects.equals(email, user.email)
        && Objects.equals(pswd, user.pswd);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, pswd);
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", userName='"
        + username
        + '\''
        + ", email='"
        + email
        + '\''
        + ", pswd='"
        + pswd
        + '\''
        + '}';
  }
}
