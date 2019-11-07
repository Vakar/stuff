package vakar.space.stuff.ui.springmvc.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import vakar.space.stuff.ui.springmvc.constraint.UniqueUserEmail;
import vakar.space.stuff.ui.springmvc.constraint.UniqueUsername;

import javax.validation.constraints.Pattern;

public class RegistrationModel {

  private static final String REGEX_PSWD_VALIDATION = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

  @NotBlank
  @Length(max = 64)
  @UniqueUsername
  private String userName;

  @NotBlank
  @UniqueUserEmail
  @Email private String eMail;

  @Length(min = 8)
  @Pattern(regexp = REGEX_PSWD_VALIDATION)
  private String password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
