package userapp.domain.annotations;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

@Component
public  class EmailValidator implements ConstraintValidator<Email, String> {
    private final String INVALID_EMAIL_MESSAGE = "Invalid email!";
    private Pattern pattern;

    public EmailValidator() {
    }

    @Override
    public void initialize(Email email) {
        this.pattern = Pattern.compile(email.regex());
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext validatorContext) {
        if (input == null) {
            return false;
        }

        if (!this.pattern.matcher(input).matches()) {
            AnnotationsUtil.setErrorMessage(validatorContext, INVALID_EMAIL_MESSAGE);
            return false;
        }

        return true;
    }
}
