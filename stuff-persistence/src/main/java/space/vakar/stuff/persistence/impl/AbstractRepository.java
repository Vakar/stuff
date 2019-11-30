package space.vakar.stuff.persistence.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import space.vakar.stuff.persistence.api.DomainEntity;
import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;

class AbstractRepository<T extends DomainEntity> implements Repository<T> {

  private static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";

  private SessionFactory sessionFactory = buildSessionFactory();

  @Override
  public void add(T entity) {
    getSession().beginTransaction();
    getSession().save(entity);
    getSession().getTransaction().commit();
  }

  @Override
  public void add(Iterable<T> entities) {
    Session session = getSession();
    session.beginTransaction();
    entities.forEach(session::save);
    session.getTransaction().commit();
  }

  @Override
  public void update(T entity) {
    getSession().beginTransaction();
    getSession().update(entity);
    getSession().getTransaction().commit();
  }

  @Override
  public void remove(T entity) {
    getSession().beginTransaction();
    getSession().delete(entity);
    getSession().getTransaction().commit();
  }

  @SuppressWarnings("unchecked")
  @Override
  public void remove(Hql hql) {
    getSession().beginTransaction();
    String hqlStr = hql.getHql();
    Query<T> query = getSession().createQuery(hqlStr);
    query.executeUpdate();
    getSession().getTransaction().commit();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<T> query(Hql hql) {
    getSession().beginTransaction();
    Query<T> query = getSession().createQuery(hql.getHql());
    List<T> list = query.list();
    getSession().getTransaction().commit();
    return list;
  }

  private static SessionFactory buildSessionFactory() {
    Configuration configObj = new Configuration();
    configObj.configure(HIBERNATE_CONFIG);
    configObj.addAnnotatedClass(User.class);
    configObj.addAnnotatedClass(Stuff.class);
    ServiceRegistry serviceRegistryObj =
        new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();
    return configObj.buildSessionFactory(serviceRegistryObj);
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }
}
