package space.vakar.stuff.ui.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import space.vakar.stuff.ui.springmvc.model.StuffDto;
import space.vakar.stuff.ui.springmvc.presenter.StuffPresenter;

@Controller
@RequestMapping("/stuff")
public class StuffViewController {

  private StuffPresenter stuffPresenter;

  @Autowired
  public StuffViewController(StuffPresenter stuffPresenter) {
    this.stuffPresenter = stuffPresenter;
  }

  @PostMapping("/seeView")
  public ModelAndView seeStuff(@RequestParam("id") int id) {
    ModelAndView modelAndView = new ModelAndView(Views.STUFF_VIEW_PAGE);
    StuffDto stuff = stuffPresenter.readById(id);
    modelAndView.addObject("stuff", stuff);
    modelAndView.addObject("isPictureExists", stuffPresenter.isPictureExists(id));
    return modelAndView;
  }

  @GetMapping(value = "/picture/{id}")
  @ResponseBody
  public byte[] picture(@PathVariable int id) {
    return stuffPresenter.getPictureById(id);
  }
}
