package space.vakar.stuff.persistence.impl;

import java.util.List;
import java.util.Optional;

import space.vakar.stuff.persistence.api.ServiceResetPassword;
import space.vakar.stuff.persistence.model.ResetPassword;
import space.vakar.stuff.persistence.model.User;

public class ServiceResetPasswordImp implements ServiceResetPassword {

  private static final String ID_FIELD = "id";
  private static final String USER_ID_FIELD = "user";

  private Repository<ResetPassword> repository = new RepositoryResetPassword();

  @Override
  public void save(ResetPassword resetPassword) {
    repository.add(resetPassword);
  }

  @Override
  public void removeByUser(User user) {
    int userId = user.getId();
    Hql hql = new HqlFindByFieldValue(ResetPassword.class, USER_ID_FIELD, String.valueOf(userId));
    List<ResetPassword> tokens = repository.query(hql);
    tokens.forEach(token -> repository.remove(token));
  }

  @Override
  public Optional<ResetPassword> findById(String uuid) {
    Hql hql = new HqlFindByFieldValue(ResetPassword.class, ID_FIELD, uuid);
    List<ResetPassword> tokens = repository.query(hql);
    return tokens.isEmpty() ? Optional.empty() : Optional.of(tokens.get(0));
  }
}
