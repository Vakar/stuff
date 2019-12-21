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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.vakar.stuff.persistence.model.PasswordRecoveryToken;
import space.vakar.stuff.persistence.model.Stuff;
import space.vakar.stuff.persistence.model.User;

class AbstractRepository<T extends Serializable> implements Repository<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);

  private static final String HIBERNATE_CONFIG = "hibernate.cfg.xml";

  private SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    Configuration config = new Configuration();
    config.configure(HIBERNATE_CONFIG);
    config.addAnnotatedClass(User.class);
    config.addAnnotatedClass(Stuff.class);
    config.addAnnotatedClass(PasswordRecoveryToken.class);
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
  @SuppressWarnings("unchecked")
  public void remove(Hql hql) {
    Transaction transaction = null;
    Session session = getSession();
    try {
      transaction = session.beginTransaction();
      Query<T> query = session.createQuery(hql.getHql());
      query.executeUpdate();
      transaction.commit();
    } catch (RuntimeException e) {
      if (transaction != null) {
        transaction.rollback();
      }
      LOGGER.error("Error happens during database transaction with hql: {}", hql.getHql());
      LOGGER.error(e.getMessage());
      throw new RepositoryException(e.getMessage(), e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<T> query(Hql hql) {
    List<T> list;
    Transaction t = null;
    Session session = getSession();
    try {
      t = session.beginTransaction();
      Query<T> query = session.createQuery(hql.getHql());
      list = query.list();
      t.commit();
    } catch (RuntimeException e) {
      if (t != null) {
        t.rollback();
      }
      LOGGER.error("Error happens during database transaction with hql: {}", hql.getHql());
      LOGGER.error(e.getMessage());
      throw new RepositoryException(e.getMessage(), e);
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return list;
  }

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  private BiConsumer<T, BiConsumer<Session, T>> entityConsumer =
      (entity, operation) -> {
        Transaction t = null;
        Session session = getSession();
        try {
          t = session.beginTransaction();
          operation.accept(session, entity);
          t.commit();
        } catch (RuntimeException e) {
          if (t != null) {
            t.rollback();
          }
          LOGGER.error("Error happens during database transaction with entity: {}", entity);
          LOGGER.error(e.getMessage());
          throw new RepositoryException(e.getMessage(), e);
        } finally {
          if (session != null) {
            session.close();
          }
        }
      };
}
