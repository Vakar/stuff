package space.vakar.stuff.springmvc.constraint;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import space.vakar.stuff.springmvc.model.ResetPasswordForm;

@Component
public class PasswordValidator implements Validator {

  private static final String REGEX_PSWD_VALIDATION = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

  @Override
  public boolean supports(Class clazz) {
    return ResetPasswordForm.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "password", "required.password", "Field name is required.");
    ValidationUtils.rejectIfEmptyOrWhitespace(
        errors, "passwordConfirm", "required.passwordConfirm", "Field name is required.");
    ResetPasswordForm passwordForm = (ResetPasswordForm) target;
    String password = passwordForm.getPassword();
    String passwordConfirm = passwordForm.getPasswordConfirm();
    if (!password.matches(REGEX_PSWD_VALIDATION)) {
      errors.rejectValue(
          "password",
          "strong.password",
          "Password must contains at list 8 characters: numbers and letters.");
    }
    if (!passwordConfirm.matches(REGEX_PSWD_VALIDATION)) {
      errors.rejectValue(
          "passwordConfirm",
          "strong.password",
          "Password must contains at list 8 characters: numbers and letters.");
    }
    if (!password.equals(passwordConfirm)) {
      errors.rejectValue("passwordConfirm", "notmatch.password");
    }
  }
}
