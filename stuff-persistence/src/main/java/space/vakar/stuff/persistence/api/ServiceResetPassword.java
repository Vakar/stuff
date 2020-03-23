package space.vakar.stuff.persistence.api;

import java.util.Optional;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;

public interface ServiceResetPassword {

  /**
   * Save reset password token to database.
   *
   * @param resetPassword reset password token
   */
  void save(ResetPassword resetPassword);

  /**
   * Remove all tokens associated with given user.
   *
   * @param user application user
   */
  void removeByUser(User user);

  /**
   * Find reset password token by uuid.
   *
   * @param id uuid
   * @return optional of password recovery token
   */
  Optional<ResetPassword> findById(String id);
}
