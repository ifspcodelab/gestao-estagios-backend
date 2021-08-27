package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OptionalValidator implements ConstraintValidator<Optional, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Pattern pattern = Pattern.compile("^(?!\\s*$).+");
        Matcher matcher = pattern.matcher(value);
        boolean result = matcher.find();
        return result;
    }
}
