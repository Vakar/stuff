package space.vakar.stuff.captcha.api;

public interface GoogleReCaptchaService {

  boolean processResponse(String response);
}
