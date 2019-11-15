package space.vakar.stuff.ui.springmvc.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import space.vakar.stuff.ui.springmvc.presenter.UserPresenter;

public class UniqueUserEmailValidator implements ConstraintValidator<UniqueUserEmail, String> {

  @Autowired private UserPresenter userPresenter;

  public void initialize(UniqueUserEmail constraintAnnotation) {
    // Implementation not required.
  }

  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value != null && !userPresenter.isUserEmailAlreadyInUse(value);
  }
}
