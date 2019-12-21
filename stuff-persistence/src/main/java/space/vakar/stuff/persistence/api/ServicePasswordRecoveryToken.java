package space.vakar.stuff.persistence.api;

import space.vakar.stuff.persistence.model.PasswordRecoveryToken;
import space.vakar.stuff.persistence.model.User;

import java.util.Optional;
import java.util.UUID;

public interface ServicePasswordRecoveryToken {

  /**
   * Save password recovery token to database.
   *
   * @param token password recovery token
   */
  void saveToken(PasswordRecoveryToken token);

  /**
   * Remove all tokens associated with given user.
   *
   * @param user application user
   */
  void removeTokenByUser(User user);

  /**
   * Find password recovery token by token uuid.
   *
   * @param token token uuid
   * @return optional of password recovery token
   */
  Optional<PasswordRecoveryToken> findToken(UUID token);
}
