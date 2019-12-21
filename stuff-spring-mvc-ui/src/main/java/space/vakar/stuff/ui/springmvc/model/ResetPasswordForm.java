package space.vakar.stuff.ui.springmvc.model;

import java.util.Objects;

public class ResetPasswordForm {

  private String username;
  private String password;
  private String passwordConfirm;

  public ResetPasswordForm() {}

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

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ResetPasswordForm that = (ResetPasswordForm) o;
    return Objects.equals(username, that.username)
        && Objects.equals(password, that.password)
        && Objects.equals(passwordConfirm, that.passwordConfirm);
  }

  @Override
  public int hashCode() {
    return Objects.hash(username, password, passwordConfirm);
  }

  @Override
  public String toString() {
    return "ResetPasswordForm{"
        + "username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", passwordConfirm='"
        + passwordConfirm
        + '\''
        + '}';
  }
}
