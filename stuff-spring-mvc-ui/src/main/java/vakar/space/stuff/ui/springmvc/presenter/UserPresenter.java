package vakar.space.stuff.ui.springmvc.presenter;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.api.ServiceUser;
import space.vakar.stuff.persistence.model.User;
import vakar.space.stuff.ui.springmvc.model.RegistrationModel;

@Component
public class UserPresenter {

  private ServiceUser serviceUser;

  @Autowired
  public UserPresenter(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
  }

  public void saveUser(RegistrationModel model) {
    String userName = model.getUsername();
    String email = model.getEmail();
    String pswd = model.getPassword();
    User user = new User(userName, email, pswd);
    serviceUser.add(user);
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
}
