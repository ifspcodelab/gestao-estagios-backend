package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.commons;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Cep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class CepTestObject {
    @Cep
    String cep;

    public CepTestObject(String cep) {
        this.cep = cep;
    }
}


public class CepTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"07813070", "07813-070", "13010150", "13010-150"})
    public void cepMustBeValid(String cep) {
        assertThat(validator.validate(new CepTestObject(cep))).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0781307", "078130700", "07813--070", "13-010150", "aaa13-070", "aaaaaaaa", "aaaaa-aaa"})
    public void cepIsInvalid(String cep) {
        assertThat(validator.validate(new CepTestObject(cep))).isNotEmpty();
    }
}
