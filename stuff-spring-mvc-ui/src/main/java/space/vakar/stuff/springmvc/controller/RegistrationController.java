package space.vakar.stuff.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.captcha.api.GoogleReCaptchaService;
import space.vakar.stuff.captcha.model.GoogleReCaptchaKeyHolder;
import space.vakar.stuff.springmvc.model.RegistrationForm;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

@Controller
public class RegistrationController {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

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
    RegistrationForm form = new RegistrationForm();
    String siteKey = googleReCaptchaKeyHolder.getSiteKey();
    form.setReCaptchaSiteKey(siteKey);
    modelAndView.addObject("registration", form);
    return modelAndView;
  }

  @PostMapping(value = "/registerUser")
  public String registerUser(
      @ModelAttribute("registration") @Validated RegistrationForm registration,
      BindingResult bindingResult,
      HttpServletRequest request) {
    String response = request.getParameter("g-recaptcha-response");
    if (!isCaptchaSuccess(response) || bindingResult.hasErrors()) {
      return REGISTRATION_VIEW_PAGE;
    }
    userPresenter.registerUser(registration);
    LOGGER.info("Successfully save new user with username: {}", registration.getUsername());
    return Views.LOGIN_PAGE;
  }

  private boolean isCaptchaSuccess(String resp) {
    return resp == null || googleReCaptchaService.processResponse(resp);
  }

  @ModelAttribute("registration")
  public RegistrationForm createStudentModel() {
    return new RegistrationForm();
  }
}
