package space.vakar.stuff.persistence.impl;

import space.vakar.stuff.persistence.api.ServicePasswordRecoveryToken;
import space.vakar.stuff.persistence.model.PasswordRecoveryToken;
import space.vakar.stuff.persistence.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServicePasswordRecoveryTokenImp implements ServicePasswordRecoveryToken {

  private static final String USER_ID_FIELD = "USER_ID";

  private Repository<PasswordRecoveryToken> repository = new RepositoryPasswordRecoveryToken();

  @Override
  public void saveToken(PasswordRecoveryToken token) {
    repository.add(token);
  }

  @Override
  public void removeTokenByUser(User user) {
    int userId = user.getId();
    Hql hql =
        new HqlFindByFieldValue(PasswordRecoveryToken.class, USER_ID_FIELD, String.valueOf(userId));
    List<PasswordRecoveryToken> tokens = repository.query(hql);
    tokens.forEach(token -> repository.remove(token));
  }

  @Override
  public Optional<PasswordRecoveryToken> findToken(UUID token) {
    Hql hql = new HqlFindByFieldValue(PasswordRecoveryToken.class, "token", token.toString());
    List<PasswordRecoveryToken> tokens = repository.query(hql);
    return tokens.isEmpty() ? Optional.empty() : Optional.of(tokens.get(0));
  }
}
