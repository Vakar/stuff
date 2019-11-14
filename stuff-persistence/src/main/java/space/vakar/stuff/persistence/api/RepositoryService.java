package space.vakar.stuff.persistence.api;

import java.util.List;

public interface RepositoryService<T> {

  /**
   * Save entity in database.
   *
   * @param entity object that you want to save in database
   */
  void add(T entity);

  /**
   * Save multiple entities in database.
   *
   * @param entities objects that you want to save in database
   */
  void add(Iterable<T> entities);

  /**
   * Update entity in database.
   *
   * @param entity object that you want to update in database
   */
  void update(T entity);

  /**
   * Remove entity from database.
   *
   * @param entity object that you want to remove from database
   */
  void remove(T entity);

  /**
   * Remove entity from database by id.
   *
   * @param id entity id
   */
  void remove(int id);

  /**
   * Retrieve entity by id.
   *
   * @param id entity id
   * @return entity from database by id
   */
  T readById(int id);

  /**
   * Retrieve all entities from database.
   *
   * @return list of entities
   */
  List<T> readAll();
}
