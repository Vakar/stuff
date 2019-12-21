package space.vakar.stuff.ui.springmvc.model;

import org.hibernate.validator.constraints.Email;
import space.vakar.stuff.ui.springmvc.constraint.UserEmailExists;

import java.util.Objects;

public class ResetPasswordRequestForm {

  @Email @UserEmailExists private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResetPasswordRequestForm that = (ResetPasswordRequestForm) o;
    return Objects.equals(email, that.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }

  @Override
  public String toString() {
    return "ResetPasswordRequestForm{" + "email='" + email + '\'' + '}';
  }
}
