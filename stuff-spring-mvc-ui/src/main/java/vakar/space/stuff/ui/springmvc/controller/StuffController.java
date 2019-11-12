package vakar.space.stuff.ui.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.persistence.model.User;
import vakar.space.stuff.ui.springmvc.model.StuffDto;
import vakar.space.stuff.ui.springmvc.presenter.StuffPresenter;
import vakar.space.stuff.ui.springmvc.presenter.UserPresenter;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/stuff")
public class StuffController {

  private static final String ADD_OR_EDIT_STUFF_PAGE = "/stuff/addOrEditStuff";
  private static final String STUFF_LIST_PAGE = "/stuff/stuffList";

  private StuffPresenter stuffPresenter;
  private UserPresenter userPresenter;

  @Autowired
  public StuffController(StuffPresenter stuffPresenter, UserPresenter userPresenter) {
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
    return new ModelAndView(STUFF_LIST_PAGE, "stuffDtoList", stuffDtoList);
  }

  @GetMapping("/addView")
  public ModelAndView addView() {
    return new ModelAndView(ADD_OR_EDIT_STUFF_PAGE, "stuffDto", new StuffDto());
  }

  @PostMapping("/editView")
  public ModelAndView edit(@RequestParam("id") int id) {
    StuffDto stuffDto = stuffPresenter.readById(id);
    return new ModelAndView(ADD_OR_EDIT_STUFF_PAGE, "stuffDto", stuffDto);
  }

  @PostMapping("/save")
  public String addStuff(
      @ModelAttribute("stuffDto") @Validated StuffDto stuffDto,
      BindingResult bindingResult,
      Principal principal) {
    if (bindingResult.hasErrors()) {
      return ADD_OR_EDIT_STUFF_PAGE;
    }
    String username = principal.getName();
    stuffPresenter.save(stuffDto, username);
    return "redirect:/stuff/list";
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("id") int id) {
    stuffPresenter.delete(id);
    return "redirect:/stuff/list";
  }
}
