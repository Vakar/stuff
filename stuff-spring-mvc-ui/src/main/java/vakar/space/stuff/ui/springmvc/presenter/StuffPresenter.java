package vakar.space.stuff.ui.springmvc.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.api.StuffRepositoryService;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;
import vakar.space.stuff.ui.springmvc.model.StuffDto;
import vakar.space.stuff.ui.springmvc.util.Mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StuffPresenter {

  private StuffRepositoryService stuffService;
  private UserRepositoryService userService;

  @Autowired
  public StuffPresenter(StuffRepositoryService stuffService, UserRepositoryService userService) {
    this.stuffService = stuffService;
    this.userService = userService;
  }

  public List<StuffDto> findStuffByUser(User user) {
    int id = user.getId();
    return stuffService.findStuffByUserId(id).stream()
        .map(Mapper::from)
        .collect(Collectors.toList());
  }

  public void save(StuffDto model, String username) {
    Stuff stuff = Mapper.from(model);
    Optional<User> userOptional = userService.findUserByUsername(username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      stuff.setUser(user);
    }
    if (stuff.getId() > 0) {
      stuffService.update(stuff);
    } else {
      stuffService.add(stuff);
    }
  }

  public void delete(int id) {
    stuffService.remove(id);
  }

  public StuffDto readById(int id) {
    Stuff stuff = stuffService.readById(id);
    return Mapper.from(stuff);
  }
}
