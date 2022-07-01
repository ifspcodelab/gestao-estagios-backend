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

    private Department department;
    private Course course;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        Campus campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = CourseFactoryUtils.sampleCourse(department);
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdExcludedId()
    {
        Course course0 = CourseFactoryUtils.sampleCourse(department);
        Course course1 = CourseFactoryUtils.sampleCourse(department);
        entityManager.persistAndFlush(course0);
        entityManager.persistAndFlush(course1);

        boolean exists = courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
                course0.getAbbreviation(),
                course0.getDepartment().getId(),
                course0.getId());

        assertThat(exists).isTrue();
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdExcludedId_ReturnsFalse_WhenAbbreviationDiffers()
    {
        Course course0 = CourseFactoryUtils.sampleCourse(department);
        Course course1 = CourseFactoryUtils.sampleCourse(department);
        course1.setAbbreviation("TCP");
        entityManager.persistAndFlush(course0);
        entityManager.persistAndFlush(course1);

        boolean exists = courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
                course0.getAbbreviation(),
                course0.getDepartment().getId(),
                course0.getId());

        assertThat(exists).isFalse();
    }
}
