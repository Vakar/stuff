package space.vakar.stuff.captcha.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import space.vakar.stuff.captcha.api.GoogleReCaptchaService;
import space.vakar.stuff.captcha.model.GoogleReCaptchaKeyHolder;
import space.vakar.stuff.captcha.model.GoogleResponse;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Service
public class GoogleReCaptchaServiceImp implements GoogleReCaptchaService {

  private GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder;
  private RestOperations restTemplate;
  private HttpServletRequest request;

  @Autowired
  public GoogleReCaptchaServiceImp(
      GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder,
      RestOperations restTemplate,
      HttpServletRequest request) {
    this.googleReCaptchaKeyHolder = googleReCaptchaKeyHolder;
    this.restTemplate = restTemplate;
    this.request = request;
  }

  @Override
  public boolean processResponse(String response) {
    String secretKey = googleReCaptchaKeyHolder.getSecretKey();
    URI verifyUri =
        URI.create(
            String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                secretKey, response, getClientIP()));
    GoogleResponse googleResponse =
        restTemplate.getForObject(verifyUri, GoogleResponse.class);
    return googleResponse.isSuccess();
  }

  private String getClientIP() {
    final String xfHeader = request.getHeader("X-Forwarded-For");
    if (xfHeader == null) {
      return request.getRemoteAddr();
    }
    return xfHeader.split(",")[0];
  }
}
