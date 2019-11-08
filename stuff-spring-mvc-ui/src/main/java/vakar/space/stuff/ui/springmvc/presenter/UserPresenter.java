package vakar.space.stuff.ui.springmvc.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.model.User;
import vakar.space.stuff.ui.springmvc.model.RegistrationModel;

import java.util.Optional;

@Component
public class UserPresenter {

  private UserRepositoryService userService;

  @Autowired
  public UserPresenter(UserRepositoryService userService) {
    this.userService = userService;
  }

  public void saveUser(RegistrationModel model) {
    String userName = model.getUserName();
    String email = model.geteMail();
    String pswd = model.getPassword();
    User user = new User(userName, email, pswd);
    userService.add(user);
  }

  public boolean isUserNameAlreadyInUse(String userName) {
    return userService.isUserNameAlreadyInUse(userName);
  }

  public boolean isUserEmailAlreadyInUse(String userEmail) {
    return userService.isUserEmailAlreadyInUse(userEmail);
  }

  public Optional<User> findUserByUsername(String username) {
    return userService.findUserByUsername(username);
  }
}
