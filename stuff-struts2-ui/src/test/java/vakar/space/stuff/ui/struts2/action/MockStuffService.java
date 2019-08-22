package vakar.space.stuff.ui.struts2.action;

import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.model.Stuff;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class MockStuffService implements RepositoryService<Stuff> {

  static final MockStuffService MOCK = new MockStuffService();

  @Override
  public void add(Stuff entity) {}

  @Override
  public void add(Iterable<Stuff> entities) {}

  @Override
  public void update(Stuff entity) {}

  @Override
  public void remove(Stuff entity) {}

  @Override
  public void remove(int id) {}

  @Override
  public Stuff readById(int id) {
    return new Stuff(0, "", BigDecimal.ZERO);
  }

  @Override
  public List<Stuff> readAll() {
    Stuff stuff = new Stuff(0, "", BigDecimal.ZERO);
    return Collections.singletonList(stuff);
  }
}
