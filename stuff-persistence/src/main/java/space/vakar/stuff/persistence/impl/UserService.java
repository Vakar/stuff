package space.vakar.stuff.persistence.impl;

import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.api.UserRepositoryService;
import space.vakar.stuff.persistence.impl.hql.HqlFindByFieldValue;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.User;

import java.util.List;

public class UserService implements UserRepositoryService {

    private Repository<User> repository = new RepositoryUser();

    static final String FIELD_USER_NAME = "userName";

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
        if (!userList.isEmpty()) user = userList.get(0);
        return user;
    }

    @Override
    public List<User> readAll() {
        return repository.query(new HqlGetAll(User.class));
    }

    @Override
    public boolean isUserNameAlreadyInUse(String userName) {
        Hql findByFieldValue = new HqlFindByFieldValue(User.class, FIELD_USER_NAME, userName);
        List<User> users = repository.query(findByFieldValue);
        return !users.isEmpty();
    }
}
