package space.vakar.stuff.springmvc.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.vakar.stuff.springmvc.model.StuffDto;
import space.vakar.stuff.springmvc.presenter.StuffPresenter;

@Controller
@RequestMapping("/stuff")
public class StuffAddOrEditController {

  private static final Logger LOGGER = LoggerFactory.getLogger(StuffAddOrEditController.class);

  private StuffPresenter stuffPresenter;

  @Autowired
  public StuffAddOrEditController(StuffPresenter stuffPresenter) {
    this.stuffPresenter = stuffPresenter;
  }

  @PostMapping("/save")
  public String addStuff(
      @ModelAttribute("stuffDto") @Validated StuffDto stuffDto,
      BindingResult bindingResult,
      Principal principal) {
    if (bindingResult.hasErrors()) {
      return Views.STUFF_ADD_OR_EDIT_PAGE;
    }
    String username = principal.getName();
    stuffPresenter.save(stuffDto, username);
    LOGGER.info("Successfully save stuff: {}", stuffDto);
    return "redirect:/stuff/list";
  }
}
