package space.vakar.stuff.persistence.api;

import java.util.List;

public interface Repository<T extends DomainEntity> {

  void add(T entity);

  void add(Iterable<T> entities);

  void update(T entity);

  void remove(T entity);

  void remove(Hql hql);

  List<T> query(Hql hql);
}