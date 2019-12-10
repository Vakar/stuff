package space.vakar.stuff.persistence.impl;

import java.util.List;

interface Repository<T> {

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
   * Remove entity from database using hql.
   *
   * @param hql hql query object
   */
  void remove(Hql hql);

  /**
   * Query entities from database using specific hql object.
   *
   * @param hql specific hql object
   * @return list of entities
   */
  List<T> query(Hql hql);
}
