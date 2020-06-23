package space.vakar.stuff.springmvc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private AuthenticationProvider customAuthProvider;
  @Autowired private UserDetailsService userDetailsService;

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(customAuthProvider);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAccessDeniedHandler accessDeniedHandler = new CustomAccessDeniedHandler();
    http.authorizeRequests()
        .antMatchers("/stuff/**")
        .access("hasRole('USER')")
        .and()
        .formLogin()
        .loginPage("/")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/stuff/list", true)
        .usernameParameter("username")
        .passwordParameter("password")
        .and()
        .logout()
        .logoutUrl("/logout")
        .deleteCookies("JSESSIONID")
        .and()
        .rememberMe()
        .userDetailsService(userDetailsService)
        .and()
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler);
  }

  static class CustomAccessDeniedHandler implements AccessDeniedHandler {
    public void handle(
        HttpServletRequest req,
        HttpServletResponse res,
        AccessDeniedException accessDeniedException)
        throws IOException {
      res.sendRedirect(req.getContextPath() + "/");
    }
  }
}
