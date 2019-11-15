package space.vakar.stuff.ui.springmvc.captcha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource("classpath:googleReCaptcha.properties")
public class GoogleReCaptchaConfig {

  private static final String SITE_KEY = "google.recaptcha.key.site";
  private static final String SECRET_KEY = "google.recaptcha.key.secret";

  private Environment env;

  @Autowired
  public GoogleReCaptchaConfig(Environment env) {
    this.env = env;
  }

  @Bean
  @Scope("singleton")
  public GoogleReCaptchaKeyHolder googleReCaptcha() {
    GoogleReCaptchaKeyHolder captcha = new GoogleReCaptchaKeyHolder();
    captcha.setSiteKey(env.getProperty(SITE_KEY));
    captcha.setSecretKey(env.getProperty(SECRET_KEY));
    return captcha;
  }

  @Bean
  public RestOperations restOperations() {
    return new RestTemplate();
  }
}
