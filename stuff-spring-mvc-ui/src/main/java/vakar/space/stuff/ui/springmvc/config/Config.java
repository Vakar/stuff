package vakar.space.stuff.ui.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import space.vakar.stuff.persistence.api.ServiceStuff;
import space.vakar.stuff.persistence.api.ServiceUser;
import space.vakar.stuff.persistence.impl.ServiceStuffImpl;
import space.vakar.stuff.persistence.impl.ServiceUserImpl;

@Configuration
public class Config {

  @Bean
  public ServiceUser userService() {
    return new ServiceUserImpl();
  }

  @Bean
  public ServiceStuff stuffService() {
    return new ServiceStuffImpl();
  }
}
