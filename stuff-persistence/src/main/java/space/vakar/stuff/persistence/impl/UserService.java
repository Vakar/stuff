package space.vakar.stuff.persistence.impl;

import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.User;

import java.util.List;

public class UserService implements RepositoryService<User> {

    private Repository<User> repository = new RepositoryUser();

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

}
