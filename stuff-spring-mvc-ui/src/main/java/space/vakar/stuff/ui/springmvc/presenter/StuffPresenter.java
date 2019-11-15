package space.vakar.stuff.ui.springmvc.presenter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.vakar.stuff.persistence.api.ServiceStuff;
import space.vakar.stuff.persistence.api.ServiceUser;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;
import space.vakar.stuff.ui.springmvc.model.StuffDto;
import space.vakar.stuff.ui.springmvc.util.Mapper;

@Component
public class StuffPresenter {

  private ServiceStuff serviceStuff;
  private ServiceUser serviceUser;

  @Autowired
  public StuffPresenter(ServiceStuff serviceStuff, ServiceUser serviceUser) {
    this.serviceStuff = serviceStuff;
    this.serviceUser = serviceUser;
  }

  public List<StuffDto> findStuffByUser(User user) {
    int id = user.getId();
    return serviceStuff.findStuffByUserId(id).stream()
        .map(Mapper::from)
        .collect(Collectors.toList());
  }

  public void save(StuffDto model, String username) {
    Stuff stuff = Mapper.from(model);
    Optional<User> userOptional = serviceUser.findUserByUsername(username);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      stuff.setUser(user);
    }
    if (stuff.getId() > 0) {
      serviceStuff.update(stuff);
    } else {
      serviceStuff.add(stuff);
    }
  }

  public void delete(int id) {
    serviceStuff.remove(id);
  }

  public StuffDto readById(int id) {
    Stuff stuff = serviceStuff.readById(id);
    return Mapper.from(stuff);
  }
}
