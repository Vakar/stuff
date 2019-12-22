package space.vakar.stuff.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.captcha.api.GoogleReCaptchaService;
import space.vakar.stuff.captcha.model.GoogleReCaptchaKeyHolder;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.springmvc.model.ResetPasswordRequestForm;
import space.vakar.stuff.springmvc.presenter.ResetPasswordPresenter;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class ForgotPasswordController {

  private static final String TITLE = "Reset Password Request";
  private static final String MESSAGE =
      "We sent message with reset password instructions to your email.";

  private static final String RESET_PASSWORD_REQUEST = "/resetPasswordRequest";

  private UserPresenter userPresenter;
  private ResetPasswordPresenter resetPasswordPresenter;
  private GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder;
  private GoogleReCaptchaService googleReCaptchaService;

  @Autowired
  public ForgotPasswordController(
      UserPresenter userPresenter,
      ResetPasswordPresenter resetPasswordPresenter,
      GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder,
      GoogleReCaptchaService googleReCaptchaService) {
    this.userPresenter = userPresenter;
    this.resetPasswordPresenter = resetPasswordPresenter;
    this.googleReCaptchaKeyHolder = googleReCaptchaKeyHolder;
    this.googleReCaptchaService = googleReCaptchaService;
  }

  @GetMapping("forgotPassword")
  public ModelAndView passwordRecovery() {
    ModelAndView modelAndView = new ModelAndView("forgotPassword");
    modelAndView.addObject("resetPasswordRequestForm", new ResetPasswordRequestForm());
    modelAndView.addObject("reCaptchaSiteKey", googleReCaptchaKeyHolder.getSiteKey());
    return modelAndView;
  }

  @PostMapping("generatePasswordResetToken")
  public String generatePasswordRecoveryToken(
      @ModelAttribute("resetPasswordRequestForm") @Validated
          ResetPasswordRequestForm resetPasswordRequestForm,
      BindingResult bindingResult,
      HttpServletRequest request,
      Model model) {
    String response = request.getParameter("g-recaptcha-response");
    if (!isCaptchaSuccess(response) || bindingResult.hasErrors()) {
      return Views.FORGOT_PASSWORD_PAGE;
    }
    String email = resetPasswordRequestForm.getEmail();
    String baseUrl = getBaseUrl(request);
    Optional<User> optional = userPresenter.findUserBeEmail(email);
    if (optional.isPresent()) {
      User user = optional.get();
      resetPasswordPresenter.createNewPasswordRecoveryTokenForUser(user, baseUrl);
      model.addAttribute("title", TITLE);
      model.addAttribute("message", MESSAGE);
    } else {
      model.addAttribute("title", "Error");
      model.addAttribute("message", "There is no user with email " + email + ".");
    }
    return "info";
  }

  private boolean isCaptchaSuccess(String resp) {
    return resp == null || googleReCaptchaService.processResponse(resp);
  }

  private String getBaseUrl(HttpServletRequest request) {
    return request.getLocalName()
        + ":"
        + request.getServerPort()
        + request.getContextPath()
        + RESET_PASSWORD_REQUEST;
  }
}
