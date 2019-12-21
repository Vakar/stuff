package space.vakar.stuff.persistence.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "PASSWORD_RECOVERY_TOKEN")
public class PasswordRecoveryToken implements Serializable {

  @NotNull @Id private UUID token;
  @NotNull @OneToOne private User user;

  public PasswordRecoveryToken() {}

  public PasswordRecoveryToken(UUID token, User user) {
    this.token = token;
    this.user = user;
  }

  public UUID getToken() {
    return token;
  }

  public void setToken(UUID token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PasswordRecoveryToken that = (PasswordRecoveryToken) o;
    return Objects.equals(token, that.token) && Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token, user);
  }

  @Override
  public String toString() {
    return "PasswordRecovery{" + "token=" + token + ", user=" + user + '}';
  }
}
