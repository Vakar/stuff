package space.vakar.stuff.persistence.api;

import java.util.List;
import space.vakar.stuff.persistence.model.Stuff;

public interface ServiceStuff extends Service<Stuff> {
  /**
   * Find all {@link Stuff} entities that belong to specific user.
   *
   * @param id user id
   * @return list of {Stuff} entities that belong to specific user
   */
  List<Stuff> findStuffByUserId(int id);
}
