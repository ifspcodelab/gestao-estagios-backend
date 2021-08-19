package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Cep;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepValidator implements ConstraintValidator<Cep, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile("^(([0-9]{2}\\.[0-9]{3}-[0-9]{3})|([0-9]{2}[0-9]{3}-[0-9]{3})|([0-9]{8}))$");
        Matcher matcher = pattern.matcher(value);
        boolean result = matcher.find();
        return result;
    }
}
