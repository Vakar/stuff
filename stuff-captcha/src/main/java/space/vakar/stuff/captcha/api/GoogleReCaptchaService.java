package space.vakar.stuff.captcha.api;

public interface GoogleReCaptchaService {

  /**
   * This method validate user via google recapture API and return result as boolean value.
   * @param response string from g-recaptcha-response parameter
   * @return true if user not a robot else false
   */
  boolean processResponse(String response);
}
