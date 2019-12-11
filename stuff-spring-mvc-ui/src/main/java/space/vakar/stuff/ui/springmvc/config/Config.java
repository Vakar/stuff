package space.vakar.stuff.ui.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(10485760); // 10MB
    multipartResolver.setMaxUploadSizePerFile(1048576); // 1MB
    return multipartResolver;
  }
}
