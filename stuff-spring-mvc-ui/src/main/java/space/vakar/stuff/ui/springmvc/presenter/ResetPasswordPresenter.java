package space.vakar.stuff.ui.springmvc.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.email.api.EmailService;
import space.vakar.stuff.email.model.Email;
import space.vakar.stuff.persistence.api.ServiceResetPassword;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;

import java.util.Optional;

@Component
public class ResetPasswordPresenter {

  private EmailService emailService;
  private ServiceResetPassword serviceResetPassword;

  @Autowired
  public ResetPasswordPresenter(
      EmailService emailService,
      ServiceResetPassword serviceResetPassword) {
    this.emailService = emailService;
    this.serviceResetPassword = serviceResetPassword;
  }

  public void createNewPasswordRecoveryTokenForUser(User user, String baseUrl) {
    ResetPassword recoveryToken = new ResetPassword(user);
    serviceResetPassword.save(recoveryToken);
    Email email = createResetPasswordEmail(recoveryToken.getId(), user.getEmail(), baseUrl);
    emailService.sendEmail(email);
  }

  private Email createResetPasswordEmail(String token, String emailAddress, String baseUrl) {
    String resetPasswordUrl = "http://" + baseUrl + "?token=" + token;
    return new Email(emailAddress, "Password Recovery", resetPasswordUrl);
  }

  public Optional<ResetPassword> getById(String id) {
    return serviceResetPassword.findById(id);
  }
}
