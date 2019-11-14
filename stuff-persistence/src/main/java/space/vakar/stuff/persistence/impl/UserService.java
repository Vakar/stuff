package space.vakar.stuff.persistence.impl;

import java.util.List;
import java.util.Optional;
import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.model.User;

public class UserService implements UserRepositoryService {

  private Repository<User> repository = new RepositoryUser();

  static final String FIELD_USER_NAME = "username";
  static final String FIELD_USER_EMAIL = "email";

  @Override
  public void add(User entity) {
    repository.add(entity);
  }

  @Override
  public void add(Iterable<User> entities) {
    repository.add(entities);
  }

  @Override
  public void update(User entity) {
    repository.update(entity);
  }

  @Override
  public void remove(User entity) {
    repository.remove(entity);
  }

  @Override
  public void remove(int id) {
    repository.remove(new HqlRemoveById(User.class, id));
  }

  @Override
  public User readById(int id) {
    List<User> userList = repository.query(new HqlGetById(User.class, id));
    User user = new User(0, "", "", "");
    if (!userList.isEmpty()) {
      user = userList.get(0);
    }
    return user;
  }

  @Override
  public List<User> readAll() {
    return repository.query(new HqlGetAll(User.class));
  }

  @Override
  public boolean isUserNameAlreadyInUse(String username) {
    Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_NAME, username);
    List<User> users = repository.query(findByFieldValue);
    return !users.isEmpty();
  }

  @Override
  public boolean isUserEmailAlreadyInUse(String userEmail) {
    Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_EMAIL, userEmail);
    List<User> users = repository.query(findByFieldValue);
    return !users.isEmpty();
  }

  @Override
  public Optional<User> findUserByUsername(String username) {
    Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_NAME, username);
    List<User> users = repository.query(findByFieldValue);
    return users.isEmpty() ? Optional.empty() : Optional.of(users.get(0));
  }
}
