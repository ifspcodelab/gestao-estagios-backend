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

    private Campus campus;
    private Department department;
    private Course course;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
    }

    @Test
    public void disableAllByDepartmentId() {
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        Department department1 = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        Course course3 = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));

        courseRepository.disableAllByDepartmentId(department.getId());

        List<Course> courses = courseRepository.findAllByStatus(EntityStatus.ENABLED);
        assertThat(courses)
                .hasSize(1)
                .contains(course3);
    }

    @Test
    public void notDisableAllByDepartmentId() {
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        Department department1 = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        Course course2 = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        Course course3 = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));

        courseRepository.disableAllByDepartmentId(UUID.randomUUID());

        List<Course> courses = courseRepository.findAllByStatus(EntityStatus.ENABLED);
        assertThat(courses)
                .hasSize(3)
                .contains(course, course2, course3);
    }

    @Test
    public void existsByDepartmentId() {
        course = CourseFactoryUtils.sampleCourse(department);
        entityManager.persistAndFlush(course);

        boolean result = courseRepository.existsByDepartmentId(course.getDepartment().getId());

        assertThat(result).isTrue();
    }

    @Test
    public void notExistsByDepartmentId() {
        course = CourseFactoryUtils.sampleCourse(department);
        entityManager.persistAndFlush(course);

        boolean result = courseRepository.existsByDepartmentId(UUID.randomUUID());

        assertThat(result).isFalse();
    }

    @Test
    public void findByIdIn() {
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        Course course1 = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        Course course2 = entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        List<UUID> coursesIds = List.of(course1.getId(), course2.getId());

        List<Course> result = courseRepository.findByIdIn(coursesIds);

        assertThat(result)
                .hasSize(2)
                .contains(course1, course2);
    }

    @Test
    public void isEmptyFindByIdIn() {
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department));
        List<UUID> coursesIds = List.of(UUID.randomUUID(), UUID.randomUUID());

        List<Course> result = courseRepository.findByIdIn(coursesIds);

        assertThat(result).isEmpty();
    }
}
