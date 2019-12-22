package space.vakar.stuff.springmvc.controller;

import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.springmvc.model.StuffListDto;
import space.vakar.stuff.springmvc.presenter.StuffPresenter;
import space.vakar.stuff.springmvc.model.StuffDto;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

@Controller
@RequestMapping("/stuff")
public class StuffListController {

  private StuffPresenter stuffPresenter;
  private UserPresenter userPresenter;

  @Autowired
  public StuffListController(StuffPresenter stuffPresenter, UserPresenter userPresenter) {
    this.stuffPresenter = stuffPresenter;
    this.userPresenter = userPresenter;
  }

  @GetMapping("/list")
  public ModelAndView list(Principal principal) {
    String username = principal.getName();
    Optional<User> userOptional = userPresenter.findUserByUsername(username);
    StuffListDto stuffListDto = new StuffListDto();
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      stuffListDto = stuffPresenter.findStuffByUser(user);
    }
    return new ModelAndView(Views.STUFF_LIST_PAGE, "stuffList", stuffListDto);
  }

  @GetMapping("/addView")
  public ModelAndView addView() {
    ModelAndView modelAndView = new ModelAndView(Views.STUFF_ADD_OR_EDIT_PAGE);
    modelAndView.addObject("stuffDto", new StuffDto());
    modelAndView.addObject("isPictureExists", false);
    return modelAndView;
  }

  @PostMapping("/editView")
  public ModelAndView edit(@RequestParam("id") int id) {
    ModelAndView modelAndView = new ModelAndView(Views.STUFF_ADD_OR_EDIT_PAGE);
    StuffDto stuffDto = stuffPresenter.readById(id);
    modelAndView.addObject("stuffDto", stuffDto);
    modelAndView.addObject("isPictureExists", stuffPresenter.isPictureExists(id));
    return modelAndView;
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    stuffPresenter.delete(id);
    return "redirect:/stuff/list";
  }
}
