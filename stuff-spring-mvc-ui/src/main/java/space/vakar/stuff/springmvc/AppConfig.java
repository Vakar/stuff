package space.vakar.stuff.springmvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import space.vakar.stuff.persistence.api.ServiceResetPassword;
import space.vakar.stuff.persistence.api.ServiceStuff;
import space.vakar.stuff.persistence.api.ServiceUser;
import space.vakar.stuff.persistence.impl.ServiceResetPasswordImp;
import space.vakar.stuff.persistence.impl.ServiceStuffImpl;
import space.vakar.stuff.persistence.impl.ServiceUserImpl;

@Configuration
@PropertySource("classpath:app.properties")
public class AppConfig {

  @Value("${app.protocol}")
  private String appProtocol;

  @Value("${app.domain}")
  private String appDomain;

  @Bean
  public ServiceUser userService() {
    return new ServiceUserImpl();
  }

  @Bean
  public ServiceStuff stuffService() {
    return new ServiceStuffImpl();
  }

  @Bean
  public ServiceResetPassword passwordRecoveryTokenService() {
    return new ServiceResetPasswordImp();
  }

  @Bean
  public MultipartResolver multipartResolver() {
    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    multipartResolver.setMaxUploadSize(10485760); // 10MB
    multipartResolver.setMaxUploadSizePerFile(1048576); // 1MB
    return multipartResolver;
  }

  @Bean
  public String appUrl() {
    return appProtocol + appDomain;
  }
}
