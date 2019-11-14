package space.vakar.stuff.persistence.api;

import java.util.List;

public interface RepositoryService<T> {

  void add(T entity);

  void add(Iterable<T> entities);

  void update(T entity);

  void remove(T entity);

  void remove(int id);

  T readById(int id);

  List<T> readAll();
}
