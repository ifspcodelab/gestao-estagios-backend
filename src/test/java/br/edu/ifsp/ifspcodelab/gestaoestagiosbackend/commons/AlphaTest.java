package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.commons;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Alpha;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class AlphaTestObject {
    @Alpha
    String name;

    public AlphaTestObject(String name) {
        this.name = name;
    }
}

public class AlphaTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test", "Another Test", "", "Bragança"})
    public void alphaIsValid(String name) {
        assertThat(validator.validate(new AlphaTestObject(name))).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "Test123", "Test 123", "123Test"})
    public void alphaIsInvalid(String name) {
        assertThat(validator.validate(new AlphaTestObject(name))).isNotEmpty();
    }
}
