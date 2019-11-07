package vakar.space.stuff.ui.springmvc.constraint;

import org.springframework.beans.factory.annotation.Autowired;
import vakar.space.stuff.ui.springmvc.presenter.UserPresenter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired private UserPresenter userPresenter;

  public void initialize(UniqueUsername constraintAnnotation) {
    // Implementation not required.
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !userPresenter.isUserNameAlreadyInUse(value);
  }
}
