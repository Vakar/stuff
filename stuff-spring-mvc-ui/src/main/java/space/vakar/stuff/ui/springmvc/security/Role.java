package space.vakar.stuff.ui.springmvc.security;

public enum Role {
  ROLE_USER("ROLE_USER");

  private String value;

  private Role(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
