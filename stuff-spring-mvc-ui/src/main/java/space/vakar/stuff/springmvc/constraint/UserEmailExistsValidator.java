package space.vakar.stuff.springmvc.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import space.vakar.stuff.springmvc.presenter.UserPresenter;

public class UserEmailExistsValidator implements ConstraintValidator<UserEmailExists, String> {

  @Autowired private UserPresenter userPresenter;

  @Override
  public void initialize(UserEmailExists constraintAnnotation) {
    // Implementation not required.
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && userPresenter.isUserEmailAlreadyInUse(value);
  }
}
