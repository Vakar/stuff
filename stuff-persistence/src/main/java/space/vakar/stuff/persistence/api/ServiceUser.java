package space.vakar.stuff.persistence.api;

import space.vakar.stuff.persistence.model.User;

import java.util.Optional;

public interface ServiceUser extends Service<User> {

  /**
   * Check is user with specific name already exist or not.
   *
   * @param userName user name
   * @return true if user name already in use
   */
  boolean isUserNameAlreadyInUse(String userName);

  /**
   * Check is email already in use or not.
   *
   * @param userEmail user email
   * @return true if email already in use
   */
  boolean isUserEmailAlreadyInUse(String userEmail);

  /**
   * Find user by user name.
   *
   * @param username user name
   * @return optional user entity object
   */
  Optional<User> findUserByUsername(String username);

  /**
   * Find user by user email.
   *
   * @param email user email
   * @return optional user entity object
   */
  Optional<User> findUserByEmail(String email);
}
