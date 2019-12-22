package space.vakar.stuff.springmvc.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  private UserPresenter userPresenter;

  @Autowired
  public UserDetailsServiceImp(UserPresenter userPresenter) {
    this.userPresenter = userPresenter;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    Optional<User> userOptional = userPresenter.findUserByUsername(username);
    if (!userOptional.isPresent()) {
      throw new UsernameNotFoundException("Can't find user with name: " + username);
    }
    User user = userOptional.get();
    return new UserDetailsImp(user);
  }
}
