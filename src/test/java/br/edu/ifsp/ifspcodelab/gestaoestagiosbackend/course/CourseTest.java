package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.User;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CourseTest {

    @Test
    public void shouldHaveADefaultConstructorWithArgs() {
        Course course = new Course();
        assertThat(course).isNotNull();
    }

    @Test
    public void shouldGenerateIdWhenParameterizedConstructorIsCalled() {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        Department department = new Department("Test Department");
        Course course = new Course(name, abbreviation, numberOfPeriods, department);

        assertThat(course.getId()).isNotNull();
    }

    @Test
    public void shouldInitiateAttributesWhenParameterizedConstructorIsCalled() {
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        Department department = new Department("Test Department");
        Course course = new Course(name, abbreviation, numberOfPeriods, department);

        assertThat(course.getId()).isNotNull();
        assertThat(course.getName()).isEqualTo(name);
        assertThat(course.getAbbreviation()).isEqualTo(abbreviation);
        assertThat(course.getNumberOfPeriods()).isEqualTo(numberOfPeriods);
        assertThat(course.getDepartment()).isEqualTo(department);
    }

    @Test
    public void shouldHaveSettersAndGetters() {
        UUID id = UUID.randomUUID();
        String name = "Test Course";
        String abbreviation = "TC";
        Integer numberOfPeriods = 6;

        Course course = new Course();
        Department department = new Department("Test Department");

        course.setId(id);
        course.setName(name);
        course.setAbbreviation(abbreviation);
        course.setNumberOfPeriods(numberOfPeriods);
        course.setDepartment(department);

        assertThat(course.getId()).isEqualTo(id);
        assertThat(course.getName()).isEqualTo(name);
        assertThat(course.getAbbreviation()).isEqualTo(abbreviation);
        assertThat(course.getNumberOfPeriods()).isEqualTo(numberOfPeriods);
        assertThat(course.getDepartment()).isEqualTo(department);
    }
}
