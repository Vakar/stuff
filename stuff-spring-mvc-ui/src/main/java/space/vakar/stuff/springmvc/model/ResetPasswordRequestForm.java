package space.vakar.stuff.springmvc.model;

import org.hibernate.validator.constraints.Email;
import space.vakar.stuff.springmvc.constraint.UserEmailExists;

public class ResetPasswordRequestForm {

  @Email @UserEmailExists
  private String email;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
