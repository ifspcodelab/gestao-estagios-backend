package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.Campus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus.CampusFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.City;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.city.CityFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentFactoryUtils;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.State;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.state.StateFactoryUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CourseRepository courseRepository;

    private Course course;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        Campus campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        Department department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = CourseFactoryUtils.sampleCourse(department);
    }

    @Test
    public void disableAllByDepartmentId() {
        entityManager.persistAndFlush(course);

        courseRepository.disableAllByDepartmentId(course.getDepartment().getId());
        entityManager.clear();
        Course course1 = entityManager.find(Course.class, course.getId());

        assertThat(course1.getStatus()).isEqualTo(EntityStatus.DISABLED);
    }

    @Test
    public void notDisableAllByDepartmentId() {
        entityManager.persistAndFlush(course);

        courseRepository.disableAllByDepartmentId(UUID.randomUUID());
        entityManager.clear();
        Course course1 = entityManager.find(Course.class, course.getId());

        assertThat(course1.getStatus()).isEqualTo(EntityStatus.ENABLED);
    }

    @Test
    public void existsByDepartmentId() {
        entityManager.persistAndFlush(course);

        boolean result = courseRepository.existsByDepartmentId(course.getDepartment().getId());

        assertThat(result).isTrue();
    }

    @Test
    public void notExistsByDepartmentId() {
        entityManager.persistAndFlush(course);

        boolean result = courseRepository.existsByDepartmentId(UUID.randomUUID());

        assertThat(result).isFalse();
    }

    @Test
    public void findByIdIn() {
        entityManager.persistAndFlush(course);
        List<UUID> coursesIds = List.of(course.getId());

        List<Course> result = courseRepository.findByIdIn(coursesIds);

        assertThat(result).hasSize(1);
    }

    @Test
    public void isEmptyFindByIdIn() {
        entityManager.persistAndFlush(course);
        List<UUID> coursesIds = List.of(UUID.randomUUID());

        List<Course> result = courseRepository.findByIdIn(coursesIds);

        assertThat(result).isEmpty();
    }
}
