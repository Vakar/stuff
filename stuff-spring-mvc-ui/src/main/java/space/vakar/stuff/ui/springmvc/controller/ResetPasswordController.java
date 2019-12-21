package space.vakar.stuff.ui.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.ui.springmvc.model.ResetPasswordForm;
import space.vakar.stuff.ui.springmvc.presenter.ResetPasswordPresenter;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;
import space.vakar.stuff.ui.springmvc.validator.PasswordValidator;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ResetPasswordController {

  private static final String TITLE = "Password Updated";
  private static final String ERROR_TITLE = "Can not update password";
  private static final String MESSAGE = "Password updated. Now you can log in using new password.";
  private static final String ERROR_MESSAGE = "User does not exist.";

  private UserPresenter userPresenter;
  private PasswordEncoder passwordEncoder;
  private ResetPasswordPresenter resetPasswordPresenter;

  @Autowired
  public ResetPasswordController(
      UserPresenter userPresenter,
      PasswordEncoder passwordEncoder,
      ResetPasswordPresenter resetPasswordPresenter) {
    this.userPresenter = userPresenter;
    this.passwordEncoder = passwordEncoder;
    this.resetPasswordPresenter = resetPasswordPresenter;
  }

  @InitBinder
  protected void initBinder(WebDataBinder binder) {
    binder.setValidator(new PasswordValidator());
  }

  @GetMapping("resetPasswordRequest")
  public ModelAndView resetPasswordRequest(@RequestParam String token) {
    Optional<ResetPassword> optional = resetPasswordPresenter.getById(token);
    ModelAndView modelAndView = new ModelAndView();
    if (optional.isPresent()) {
      modelAndView.setViewName("resetPassword");
      ResetPassword resetPassword = optional.get();
      String username = resetPassword.getUser().getUsername();
      ResetPasswordForm form = new ResetPasswordForm();
      form.setUsername(username);
      modelAndView.addObject("resetPasswordForm", form);
    } else {
      modelAndView.setViewName("info");
      modelAndView.addObject("title", "Bad URL");
      modelAndView.addObject("message", "This url can't be used for password reset.");
    }
    return modelAndView;
  }

  @PostMapping("resetPassword")
  public String resetPassword(
      @Valid @ModelAttribute("resetPasswordForm") ResetPasswordForm resetPasswordForm,
      BindingResult bindingResult,
      Model model) {
    if (bindingResult.hasErrors()) {
      return "resetPassword";
    }
    String newPassword = resetPasswordForm.getPassword();
    String newPasswordHash = passwordEncoder.encode(newPassword);
    String userName = resetPasswordForm.getUsername();
    Optional<User> userOptional = userPresenter.findUserByUsername(userName);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      user.setPswd(newPasswordHash);
      userPresenter.saveUser(user);
      model.addAttribute("title", TITLE);
      model.addAttribute("message", MESSAGE);
    } else {
      model.addAttribute("title", ERROR_TITLE);
      model.addAttribute("message", ERROR_MESSAGE);
    }
    return "info";
  }
}
