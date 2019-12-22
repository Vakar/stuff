package space.vakar.stuff.springmvc.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserEmailExistsValidator.class)
@Retention(RUNTIME)
@Target({FIELD, METHOD})
public @interface UserEmailExists {
    public String message() default "There is no such email!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
