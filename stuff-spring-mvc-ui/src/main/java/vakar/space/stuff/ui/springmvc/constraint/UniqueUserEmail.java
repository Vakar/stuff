package vakar.space.stuff.ui.springmvc.constraint;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqueUserEmailValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface UniqueUserEmail {
  public String message() default "This email already used!";

  public Class<?>[] groups() default {};

  public Class<? extends Payload>[] payload() default {};
}
