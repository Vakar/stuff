package vakar.space.stuff.ui.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.vakar.stuff.persistence.api.StuffRepositoryService;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.impl.StuffService;
import space.vakar.stuff.persistence.impl.UserService;

@Configuration
public class Config {

  @Bean
  public UserRepositoryService userService() {
    return new UserService();
  }

  @Bean
  public StuffRepositoryService stuffService(){
    return new StuffService();
  }
}
