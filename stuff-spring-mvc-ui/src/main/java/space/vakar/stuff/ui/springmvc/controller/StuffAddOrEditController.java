package space.vakar.stuff.ui.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import space.vakar.stuff.ui.springmvc.model.StuffDto;
import space.vakar.stuff.ui.springmvc.presenter.StuffPresenter;

import java.security.Principal;

@Controller
@RequestMapping("/stuff")
public class StuffAddOrEditController {

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
        return "redirect:/stuff/list";
    }
}
