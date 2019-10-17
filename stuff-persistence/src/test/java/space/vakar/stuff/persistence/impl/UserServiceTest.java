package space.vakar.stuff.persistence.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import space.vakar.stuff.persistence.api.Hql;
import space.vakar.stuff.persistence.api.Repository;
import space.vakar.stuff.persistence.api.RepositoryService;
import space.vakar.stuff.persistence.impl.hql.HqlGetAll;
import space.vakar.stuff.persistence.impl.hql.HqlGetById;
import space.vakar.stuff.persistence.impl.hql.HqlRemoveById;
import space.vakar.stuff.persistence.model.User;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    private Repository<User> repository;

    @InjectMocks
    private RepositoryService<User> service = new UserService();

    private static final int ONCE = 1;

    private User user = new User(1, "user1", "user1@domain.com", "one");
    private List<User> userList = Collections.singletonList(user);

    @Test
    public void add() {
        service.add(user);
        verify(repository, times(ONCE)).add(user);
    }

    @Test
    public void testAdd() {
        service.add(userList);
        verify(repository, times(ONCE)).add(userList);
    }

    @Test
    public void update() {
        user.setUserName("user1_new");
        user.setEmail("user1_new@domain.com");
        user.setPswd("one_new");
        service.update(user);
        verify(repository, times(ONCE)).update(user);
    }

    @Test
    public void remove() {
        service.remove(user);
        verify(repository, times(ONCE)).remove(user);
    }

    @Test
    public void testRemove() {
        service.remove(user.getId());
        Hql hql = new HqlRemoveById(User.class, user.getId());
        verify(repository, times(ONCE)).remove(hql);
    }

    @Test
    public void readById() {
        Hql hql = new HqlGetById(User.class, user.getId());
        when(repository.query(hql)).thenReturn(userList);
        service.readById(user.getId());
        verify(repository, times(ONCE)).query(hql);
    }

    @Test
    public void readAll() {
        Hql hql = new HqlGetAll(User.class);
        service.readAll();
        verify(repository, times(ONCE)).query(hql);
    }
}
