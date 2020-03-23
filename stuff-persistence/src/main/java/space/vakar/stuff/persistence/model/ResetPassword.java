package space.vakar.stuff.persistence.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RESET_PASSWORD")
public class ResetPassword implements Serializable {

  @NotNull @Id private String id;
  @NotNull @OneToOne private User user;

  /** Default constructor. */
  public ResetPassword() {}

  /**
   * Constructor set id field with UUID.
   *
   * @param user user object
   */
  public ResetPassword(User user) {
    this.id = UUID.randomUUID().toString();
    this.user = user;
  }

  public ResetPassword(String id, User user) {
    this.id = id;
    this.user = user;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResetPassword that = (ResetPassword) o;
    return Objects.equals(id, that.id) && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, user);
  }

  @Override
  public String toString() {
    return "ResetPassword{" + "id='" + id + '\'' + ", user=" + user + '}';
  }
}
