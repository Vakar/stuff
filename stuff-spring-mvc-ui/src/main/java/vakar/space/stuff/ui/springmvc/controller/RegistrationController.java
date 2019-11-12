package vakar.space.stuff.ui.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import vakar.space.stuff.ui.springmvc.model.RegistrationModel;
import vakar.space.stuff.ui.springmvc.presenter.UserPresenter;
import vakar.space.stuff.ui.springmvc.captcha.GoogleReCaptchaService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationController {

  private UserPresenter userPresenter;
  private GoogleReCaptchaService googleReCaptchaService;

  @Autowired
  public RegistrationController(UserPresenter userPresenter, GoogleReCaptchaService googleReCaptchaService) {
    this.userPresenter = userPresenter;
    this.googleReCaptchaService = googleReCaptchaService;
  }

  @GetMapping(value = "/registration")
  public ModelAndView view() {
    return new ModelAndView("registration", "command", new RegistrationModel());
  }

  @PostMapping(value = "/registerUser")
  public String registerUser(
      @ModelAttribute("registration") @Validated RegistrationModel registration,
      BindingResult bindingResult,
      Model model,
      HttpServletRequest request) {
    String response = request.getParameter("g-recaptcha-response");
    boolean isCaptchaSuccess = googleReCaptchaService.processResponse(response);
    if (!isCaptchaSuccess || bindingResult.hasErrors()) {
      return "registration";
    }
    userPresenter.saveUser(registration);
    model.addAttribute("userName", registration.getUserName());
    model.addAttribute("eMail", registration.geteMail());
    model.addAttribute("password", registration.getPassword());
    return "result";
  }

  @ModelAttribute("registration")
  public RegistrationModel createStudentModel() {
    return new RegistrationModel();
  }
}
