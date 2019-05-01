package userapp.domain.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private final String INVALID_PASSWORD_LENGTH = "Password should be between 6 and 50 symbols";
    private final String INVALID_PASSWORD_NO_LOWERCASE_LETTERS = "Password should contain at least 1 lowercase letter";
    private final String INVALID_PASSWORD_NO_UPPERCASE_LETTERS = "Password should contain at least 1 uppercase letter";
    private final String INVALID_PASSWORD_NO_DIGITS = "Password should contain at least 1 digit";
    private final String INVALID_PASSWORD_NO_SPECIAL_SYMBOLS = "Password should contain at least 1 special symbol (!, @, #, $, %, ^, &, *, (, ), _, +, <, >, ?)";

    private Pattern pattern;

    public PasswordValidator() {
    }

    @Override
    public void initialize(Password password) {
        this.pattern = Pattern.compile(password.passwordSpecialSymbolRegex());
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext validatorContext) {

        if (input == null) {
            return false;
        }

        if (input.length() < 6 || input.length() > 50) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_PASSWORD_LENGTH);
            return false;
        }

        if (input.matches("[^a-z]")) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_PASSWORD_NO_LOWERCASE_LETTERS);
            return false;
        }

        if (input.matches("[^A-Z]")) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_PASSWORD_NO_UPPERCASE_LETTERS);
            return false;
        }

        if (input.matches("[^0-9]")) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_PASSWORD_NO_DIGITS);
            return false;
        }

        if (!pattern.matcher(input).find()) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_PASSWORD_NO_SPECIAL_SYMBOLS);
            return false;
        }

        return true;
    }
}
