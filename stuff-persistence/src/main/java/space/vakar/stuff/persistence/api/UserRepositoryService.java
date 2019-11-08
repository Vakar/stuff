package space.vakar.stuff.persistence.api;

import space.vakar.stuff.persistence.model.User;

import java.util.Optional;

public interface UserRepositoryService extends RepositoryService<User> {

    boolean isUserNameAlreadyInUse(String userName);
    boolean isUserEmailAlreadyInUse(String userEmail);
    Optional<User> findUserByUsername(String username);

}
