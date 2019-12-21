package space.vakar.stuff.ui.springmvc.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

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
