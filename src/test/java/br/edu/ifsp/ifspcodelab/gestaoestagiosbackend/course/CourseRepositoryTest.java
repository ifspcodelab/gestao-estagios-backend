package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CourseRepository courseRepository;
    private Campus campus;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdExcludedId()
    {
        Department department0 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department0);
        entityManager.persistAndFlush(department1);
        Course course0 = CourseFactoryUtils.sampleCourse(department0);
        Course course1 = CourseFactoryUtils.sampleCourse(department0);
        Course course2 = CourseFactoryUtils.sampleCourse(department1);
        course0.setAbbreviation("TADS");
        course1.setAbbreviation("INF");
        course2.setAbbreviation("TADS");
        entityManager.persistAndFlush(course0);
        entityManager.persistAndFlush(course1);
        entityManager.persistAndFlush(course2);

        course1.setAbbreviation("TADS");
        boolean exists = courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
                course1.getAbbreviation(),
                course1.getDepartment().getId(),
                course1.getId());

        assertThat(exists).isTrue();
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdExcludedIdReturnsFalseWhenAbbreviationDiffers()
    {
        Department department0 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department0);
        entityManager.persistAndFlush(department1);
        Course course0 = CourseFactoryUtils.sampleCourse(department0);
        Course course1 = CourseFactoryUtils.sampleCourse(department0);
        Course course2 = CourseFactoryUtils.sampleCourse(department1);
        course0.setAbbreviation("TADS");
        course1.setAbbreviation("INF");
        course2.setAbbreviation("TADS");
        entityManager.persistAndFlush(course0);
        entityManager.persistAndFlush(course1);
        entityManager.persistAndFlush(course2);

        course1.setAbbreviation("INF2");
        boolean exists = courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
                course1.getAbbreviation(),
                course1.getDepartment().getId(),
                course1.getId());

        assertThat(exists).isFalse();
    }
}
