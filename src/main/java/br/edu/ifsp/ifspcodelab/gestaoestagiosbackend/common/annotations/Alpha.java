package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators.AlphaValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = AlphaValidator.class)
public @interface Alpha {
    String message() default "deve conter apenas letras";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
