package vakar.space.stuff.ui.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.impl.UserService;

@Configuration
public class Config {

  @Bean
  @Scope("singleton")
  public UserRepositoryService userService() {
    return new UserService();
  }
}
