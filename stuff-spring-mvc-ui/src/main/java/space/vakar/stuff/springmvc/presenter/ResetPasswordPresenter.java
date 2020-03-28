package space.vakar.stuff.springmvc.presenter;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.email.api.EmailService;
import space.vakar.stuff.email.model.Email;
import space.vakar.stuff.persistence.api.ServiceResetPassword;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;

@Component
public class ResetPasswordPresenter {

  private String appUrl;
  private EmailService emailService;
  private ServiceResetPassword serviceResetPassword;

  @Autowired
  public ResetPasswordPresenter(
      String appUrl,
      EmailService emailService,
      ServiceResetPassword serviceResetPassword) {
    this.appUrl = appUrl;
    this.emailService = emailService;
    this.serviceResetPassword = serviceResetPassword;
  }

  public void createNewPasswordRecoveryTokenForUser(User user, String requestPath) {
    ResetPassword recoveryToken = new ResetPassword(user);
    serviceResetPassword.save(recoveryToken);
    Email email = createResetPasswordEmail(requestPath, recoveryToken.getId(), user.getEmail());
    emailService.sendEmail(email);
  }

  private Email createResetPasswordEmail(String requestPath, String token, String emailAddress) {
    String resetPasswordUrl = appUrl + requestPath + "?token=" + token;
    return new Email(emailAddress, "Password Recovery", resetPasswordUrl);
  }

  public Optional<ResetPassword> getById(String id) {
    return serviceResetPassword.findById(id);
  }

  public void removeTokens(User user) {
    serviceResetPassword.removeByUser(user);
  }
}
