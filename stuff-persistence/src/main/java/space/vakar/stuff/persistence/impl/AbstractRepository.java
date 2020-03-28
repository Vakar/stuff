package space.vakar.stuff.persistence.impl;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiConsumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;

class AbstractRepository<T extends Serializable> implements Repository<T> {

  private static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";

  private SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    Configuration config = new Configuration();
    config.configure(HIBERNATE_CONFIG);
    config.addAnnotatedClass(User.class);
    config.addAnnotatedClass(Stuff.class);
    config.addAnnotatedClass(ResetPassword.class);
    ServiceRegistry serviceRegistry =
        new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    return config.buildSessionFactory(serviceRegistry);
  }

  @Override
  public void add(T entity) {
    entityConsumer.accept(entity, Session::save);
  }

  @Override
  public void add(Iterable<T> entities) {
    entities.forEach(this::add);
  }

  @Override
  public void update(T entity) {
    entityConsumer.accept(entity, Session::update);
  }

  @Override
  public void remove(T entity) {
    entityConsumer.accept(entity, Session::delete);
  }

  @Override
  public void remove(Hql hql) {
    Transaction transaction = null;
    try (Session session = getSession()) {
      transaction = session.beginTransaction();
      @SuppressWarnings("unchecked") // You should write test to be sure it is work properly.
      Query<T> query = session.createQuery(hql.getHql());
      query.executeUpdate();
      transaction.commit();
    } catch (RuntimeException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      throw new RepositoryException(
          "Error happened during removing entity from database with hql: " + hql.getHql(), e);
    }
  }

  @Override
  public List<T> query(Hql hql) {
    List<T> list;
    Transaction transaction;
    try (Session session = getSession()) {
      transaction = session.beginTransaction();
      @SuppressWarnings("unchecked") // You should write test to be sure it is work properly.
      Query<T> query = session.createQuery(hql.getHql());
      list = query.list();
      transaction.commit();
    } catch (RuntimeException e) {
      throw new RepositoryException(
          "Error happens during query transaction with hql: " + hql.getHql(), e);
    }
    return list;
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  private BiConsumer<T, BiConsumer<Session, T>> entityConsumer =
      (entity, operation) -> {
        Transaction t = null;
        try (Session session = getSession()) {
          t = session.beginTransaction();
          operation.accept(session, entity);
          t.commit();
        } catch (RuntimeException e) {
          if (t != null) {
            t.rollback();
          }
          throw new RepositoryException(
              "Error happens during database transaction with hql: " + entity, e);
        }
      };
}
