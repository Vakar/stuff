package space.vakar.stuff.email.api;

import space.vakar.stuff.email.model.Email;

public interface EmailService {

  /**
   * Send email for recipient with subject and message.
   *
   * @param email email for sending
   */
  void sendEmail(Email email);
}
