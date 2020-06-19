package space.vakar.stuff.springmvc.model;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import space.vakar.stuff.springmvc.constraint.UniqueUserEmail;
import space.vakar.stuff.springmvc.constraint.UniqueUsername;

public class RegistrationForm {

  private static final String REGEX_PSWD_VALIDATION = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

  @NotBlank
  @Length(max = 64)
  @UniqueUsername
  private String username;

  @NotBlank
  @UniqueUserEmail
  @Email private String email;

  @Length(min = 8)
  @Pattern(regexp = REGEX_PSWD_VALIDATION)
  private String password;

  private String reCaptchaSiteKey;

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getReCaptchaSiteKey() {
    return reCaptchaSiteKey;
  }

  public void setReCaptchaSiteKey(String reCaptchaSiteKey) {
    this.reCaptchaSiteKey = reCaptchaSiteKey;
  }
}
