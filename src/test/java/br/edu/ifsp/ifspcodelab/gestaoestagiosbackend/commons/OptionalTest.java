package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.commons;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalTestObject {
    @Optional
    String name;

    public OptionalTestObject(String name) {
        this.name = name;
    }
}

public class OptionalTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "Test" })
    public void optionalIsValid(String name) {
        assertThat(validator.validate(new OptionalTestObject(name))).isEmpty();
    }

    @Test
    public void optionalIsInvalid() {
        assertThat(validator.validate(new OptionalTestObject(" "))).isNotEmpty();
    }

}
