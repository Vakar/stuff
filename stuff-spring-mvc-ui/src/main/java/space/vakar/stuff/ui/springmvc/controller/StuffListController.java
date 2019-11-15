package space.vakar.stuff.ui.springmvc.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.ui.springmvc.model.StuffDto;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;
import space.vakar.stuff.ui.springmvc.presenter.StuffPresenter;

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
    List<StuffDto> stuffDtoList = new ArrayList<>();
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      stuffDtoList.addAll(stuffPresenter.findStuffByUser(user));
    }
    return new ModelAndView(Views.STUFF_LIST_PAGE, "stuffDtoList", stuffDtoList);
  }

  @GetMapping("/addView")
  public ModelAndView addView() {
    return new ModelAndView(Views.STUFF_ADD_OR_EDIT_PAGE, "stuffDto", new StuffDto());
  }

  @PostMapping("/editView")
  public ModelAndView edit(@RequestParam("id") int id) {
    StuffDto stuffDto = stuffPresenter.readById(id);
    return new ModelAndView(Views.STUFF_ADD_OR_EDIT_PAGE, "stuffDto", stuffDto);
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    stuffPresenter.delete(id);
    return "redirect:/stuff/list";
  }
}
