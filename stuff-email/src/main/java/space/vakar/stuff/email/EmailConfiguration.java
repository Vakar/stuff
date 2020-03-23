package space.vakar.stuff.email;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource("classpath:email.properties")
public class EmailConfiguration {

  private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
  private static final String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
  private static final String MAIL_SMTP_HOST = "mail.smtp.host";
  private static final String MAIL_SMTP_PORT = "mail.smtp.port";
  private static final String MAIL_SMTP_SSL_TRUST = "mail.smtp.ssl.trust";

  private static final String USER_NAME = "username";
  private static final String PSWD = "password";
  private static final String FROM_EMAIL = "from.email";

  @Autowired private Environment env;

  @Bean
  public String fromEmail() {
    return env.getProperty(FROM_EMAIL);
  }

  @Bean
  public Session session() {
    return Session.getInstance(
        emailProperties(),
        new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(env.getProperty(USER_NAME), env.getProperty(PSWD));
          }
        });
  }

  private Properties emailProperties() {
    Properties prop = new Properties();
    prop.put(MAIL_SMTP_AUTH, env.getProperty(MAIL_SMTP_AUTH));
    prop.put(MAIL_SMTP_STARTTLS_ENABLE, env.getProperty(MAIL_SMTP_STARTTLS_ENABLE));
    prop.put(MAIL_SMTP_HOST, env.getProperty(MAIL_SMTP_HOST));
    prop.put(MAIL_SMTP_PORT, env.getProperty(MAIL_SMTP_PORT));
    prop.put(MAIL_SMTP_SSL_TRUST, env.getProperty(MAIL_SMTP_SSL_TRUST));
    return prop;
  }
}
