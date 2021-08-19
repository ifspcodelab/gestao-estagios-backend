package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.validators.OptionalValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Constraint(validatedBy = OptionalValidator.class)
public @interface Optional {
    String message() default "{javax.validation.constraints.NotBlank.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
