package vakar.space.stuff.ui.springmvc.model;

public class GoogleReCaptcha {

  private String siteKey;
  private String secretKey;

  public String getSiteKey() {
    return siteKey;
  }

  public void setSiteKey(String siteKey) {
    this.siteKey = siteKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }
}
