package space.vakar.stuff.ui.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.ui.springmvc.captcha.GoogleReCaptchaKeyHolder;
import space.vakar.stuff.ui.springmvc.captcha.GoogleReCaptchaService;
import space.vakar.stuff.ui.springmvc.model.RegistrationModel;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;

@Controller
public class RegistrationController {

  private static final String REGISTRATION_VIEW_PAGE = "registration";

  private GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder;
  private GoogleReCaptchaService googleReCaptchaService;
  private UserPresenter userPresenter;

  @Autowired
  public RegistrationController(
      GoogleReCaptchaKeyHolder googleReCaptchaKeyHolder,
      GoogleReCaptchaService googleReCaptchaService,
      UserPresenter userPresenter) {
    this.googleReCaptchaKeyHolder = googleReCaptchaKeyHolder;
    this.googleReCaptchaService = googleReCaptchaService;
    this.userPresenter = userPresenter;
  }

  @GetMapping(value = "/registration")
  public ModelAndView view() {
    ModelAndView modelAndView = new ModelAndView(REGISTRATION_VIEW_PAGE);
    modelAndView.addObject("command", new RegistrationModel());
    modelAndView.addObject("reCaptchaSiteKey", googleReCaptchaKeyHolder.getSiteKey());
    return modelAndView;
  }

  @PostMapping(value = "/registerUser")
  public String registerUser(
      @ModelAttribute("registration") @Validated RegistrationModel registration,
      BindingResult bindingResult,
      HttpServletRequest request) {
    String response = request.getParameter("g-recaptcha-response");
    boolean isCaptchaSuccess = googleReCaptchaService.processResponse(response);
    if (!isCaptchaSuccess || bindingResult.hasErrors()) {
      return REGISTRATION_VIEW_PAGE;
    }
    userPresenter.saveUser(registration);
    return Views.LOGIN_PAGE;
  }

  @ModelAttribute("registration")
  public RegistrationModel createStudentModel() {
    return new RegistrationModel();
  }
}
