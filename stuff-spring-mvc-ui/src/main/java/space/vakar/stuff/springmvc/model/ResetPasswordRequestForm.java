package space.vakar.stuff.springmvc.model;

import org.hibernate.validator.constraints.Email;
import space.vakar.stuff.springmvc.constraint.UserEmailExists;

public class ResetPasswordRequestForm {

  @Email @UserEmailExists
  private String email;

  private String reCaptchaSiteKey;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getReCaptchaSiteKey() {
    return reCaptchaSiteKey;
  }

  public void setReCaptchaSiteKey(String reCaptchaSiteKey) {
    this.reCaptchaSiteKey = reCaptchaSiteKey;
  }
}
