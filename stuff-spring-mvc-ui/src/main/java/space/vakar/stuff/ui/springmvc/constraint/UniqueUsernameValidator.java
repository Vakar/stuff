package space.vakar.stuff.ui.springmvc.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

  @Autowired private UserPresenter userPresenter;

  public void initialize(UniqueUsername constraintAnnotation) {
    // Implementation not required.
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !userPresenter.isUserNameAlreadyInUse(value);
  }
}
