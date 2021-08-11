package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CampusTest {

    @Test
    public void shouldGenerateIdWhenParameterizedConstructorIsCalled() {
        Campus campus = CampusFactory.sampleCampus();

        assertThat(campus.getId()).isNotNull();
    }
}
