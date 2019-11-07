package space.vakar.stuff.persistence.api;

import space.vakar.stuff.persistence.model.User;

public interface UserRepositoryService extends RepositoryService<User> {

    boolean isUserNameAlreadyInUse(String userName);
    boolean isUserEmailAlreadyInUse(String userEmail);

}
