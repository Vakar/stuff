package vakar.space.stuff.ui.springmvc.captcha;

public interface GoogleReCaptchaService {

  boolean processResponse(String response);
}
