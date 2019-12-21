package space.vakar.stuff.ui.springmvc.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.api.ServiceUser;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.ui.springmvc.model.RegistrationModel;

import java.util.Optional;

@Component
public class UserPresenter {

  private PasswordEncoder passwordEncoder;
  private ServiceUser serviceUser;

  @Autowired
  public UserPresenter(PasswordEncoder passwordEncoder, ServiceUser serviceUser) {
    this.passwordEncoder = passwordEncoder;
    this.serviceUser = serviceUser;
  }

  public void registerUser(RegistrationModel model) {
    String userName = model.getUsername();
    String email = model.getEmail();
    String pswd = model.getPassword();
    String passwordHash = passwordEncoder.encode(pswd);
    User user = new User(userName, email, passwordHash);
    serviceUser.add(user);
  }

  public void saveUser(User user) {
    serviceUser.update(user);
  }

  public boolean isUserNameAlreadyInUse(String userName) {
    return serviceUser.isUserNameAlreadyInUse(userName);
  }

  public boolean isUserEmailAlreadyInUse(String userEmail) {
    return serviceUser.isUserEmailAlreadyInUse(userEmail);
  }

  public Optional<User> findUserByUsername(String username) {
    return serviceUser.findUserByUsername(username);
  }

  public Optional<User> findUserBeEmail(String email) {
    return serviceUser.findUserByEmail(email);
  }

}
