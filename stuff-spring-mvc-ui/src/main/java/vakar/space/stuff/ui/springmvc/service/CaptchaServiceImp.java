package vakar.space.stuff.ui.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import vakar.space.stuff.ui.springmvc.model.GoogleReCaptcha;
import vakar.space.stuff.ui.springmvc.model.GoogleResponse;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Service
public class CaptchaServiceImp implements CaptchaService {

  @Autowired private GoogleReCaptcha googleReCaptcha;

  @Autowired private RestOperations restTemplate;

  @Autowired private HttpServletRequest request;

  @Override
  public boolean processResponse(String response) {
    String secretKey = googleReCaptcha.getSecretKey();
    URI verifyUri =
        URI.create(
            String.format(
                "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s",
                secretKey, response, getClientIP()));
    GoogleResponse googleResponse = restTemplate.getForObject(verifyUri, GoogleResponse.class);
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
