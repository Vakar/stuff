package space.vakar.stuff.persistence.impl;

import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.api.StuffRepositoryService;
import space.vakar.stuff.persistence.impl.hql.HqlFindByFieldValue;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.util.List;

public class StuffService implements StuffRepositoryService {

  private Repository<Stuff> repository = new RepositoryStuff();

  static final String FIELD_OWNER_ID = "owner";

  @Override
  public void add(Stuff entity) {
    repository.add(entity);
  }

  @Override
  public void add(Iterable<Stuff> entities) {
    repository.add(entities);
  }

  @Override
  public void update(Stuff entity) {
    repository.update(entity);
  }

  @Override
  public void remove(Stuff entity) {
    repository.remove(entity);
  }

  @Override
  public void remove(int id) {
    repository.remove(new HqlRemoveById(Stuff.class, id));
  }

  @Override
  public Stuff readById(int id) {
    List<Stuff> stuffList = repository.query(new HqlGetById(Stuff.class, id));
    Stuff stuff = new Stuff(0, "", BigDecimal.ZERO);
    if (!stuffList.isEmpty()) stuff = stuffList.get(0);
    return stuff;
  }

  @Override
  public List<Stuff> readAll() {
    return repository.query(new HqlGetAll(Stuff.class));
  }

  @Override
  public List<Stuff> findStuffByUserId(int id) {
    Hql hql = new HqlFindByFieldValue(Stuff.class, FIELD_OWNER_ID, String.valueOf(id));
    return repository.query(hql);
  }
}
