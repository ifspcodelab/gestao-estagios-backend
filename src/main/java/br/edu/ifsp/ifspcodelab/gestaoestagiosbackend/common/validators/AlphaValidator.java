package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Alpha;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphaValidator implements ConstraintValidator<Alpha, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^[A-Za-z\\u00C0-\\u00FF\\s]*$");
        Matcher matcher = pattern.matcher(value);
        boolean result = matcher.find();
        return result;
    }
}
