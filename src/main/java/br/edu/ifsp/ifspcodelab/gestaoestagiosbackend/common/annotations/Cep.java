package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators.CepValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = CepValidator.class)
public @interface Cep {
    String message() default "{cep.msg}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
