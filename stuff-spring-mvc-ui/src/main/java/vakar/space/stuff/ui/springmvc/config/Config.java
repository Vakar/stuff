package vakar.space.stuff.ui.springmvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.impl.UserService;
import vakar.space.stuff.ui.springmvc.model.GoogleReCaptcha;

@Configuration
@PropertySource("classpath:application.properties")
public class Config {

  private static final String SITE_KEY = "google.recaptcha.key.site";
  private static final String SECRET_KEY = "google.recaptcha.key.secret";

  private Environment env;

  @Autowired
  public Config(Environment env) {
    this.env = env;
  }

  @Bean
  @Scope("singleton")
  public UserRepositoryService userService() {
    return new UserService();
  }

  @Bean
  @Scope("singleton")
  public GoogleReCaptcha googleReCaptcha() {
    GoogleReCaptcha captcha = new GoogleReCaptcha();
    captcha.setSiteKey(env.getProperty(SITE_KEY));
    captcha.setSecretKey(env.getProperty(SECRET_KEY));
    return captcha;
  }

  @Bean
  public RestOperations restOperations() {
    return new RestTemplate();
  }
}
