package space.vakar.stuff.ui.springmvc.captcha;

public interface GoogleReCaptchaService {

  boolean processResponse(String response);
}
