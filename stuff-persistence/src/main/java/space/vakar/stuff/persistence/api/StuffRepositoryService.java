package space.vakar.stuff.persistence.api;

import java.util.List;
import space.vakar.stuff.persistence.model.Stuff;

public interface StuffRepositoryService extends RepositoryService<Stuff> {
  List<Stuff> findStuffByUserId(int id);
}
