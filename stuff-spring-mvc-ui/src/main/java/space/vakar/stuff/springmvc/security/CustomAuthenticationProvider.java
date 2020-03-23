package space.vakar.stuff.springmvc.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private PasswordEncoder passwordEncoder;
  private UserPresenter userPresenter;

  @Autowired
  public CustomAuthenticationProvider(
      PasswordEncoder passwordEncoder, UserPresenter userPresenter) {
    this.passwordEncoder = passwordEncoder;
    this.userPresenter = userPresenter;
  }

  @Override
  public Authentication authenticate(Authentication authentication) {
    String userName = authentication.getName();
    String password = authentication.getCredentials().toString();
    if (!isPasswordValid(userName, password)) {
      throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
    }
    return new UsernamePasswordAuthenticationToken(userName, password, getUserGrantedAuthorities());
  }

  private List<GrantedAuthority> getUserGrantedAuthorities() {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(Role.ROLE_USER::value);
    return grantedAuthorities;
  }

  private boolean isPasswordValid(String username, String password) {
    Optional<User> userOpt = userPresenter.findUserByUsername(username);
    return userOpt.isPresent() && passwordEncoder.matches(password, userOpt.get().getPswd());
  }

  @Override
  public boolean supports(Class<?> clazz) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
  }
}
