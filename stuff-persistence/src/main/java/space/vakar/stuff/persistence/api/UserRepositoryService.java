package space.vakar.stuff.persistence.api;

import java.util.Optional;
import space.vakar.stuff.persistence.model.User;

public interface UserRepositoryService extends RepositoryService<User> {

  boolean isUserNameAlreadyInUse(String userName);

  boolean isUserEmailAlreadyInUse(String userEmail);

  Optional<User> findUserByUsername(String username);
}
