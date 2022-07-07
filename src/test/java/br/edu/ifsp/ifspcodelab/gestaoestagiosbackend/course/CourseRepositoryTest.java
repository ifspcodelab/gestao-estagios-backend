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
class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    Campus campus;
    Department department;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
    }

    @Test
    public void findAllByStatusWithArgumentEnabledReturnsOnlyEnabledCourses() {
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseEnabled(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseEnabled(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseDisabled(department));

        List<Course> enabledCourses = courseRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(enabledCourses)
                .hasSize(2)
                .extracting(Course::getStatus)
                .containsExactlyInAnyOrder(EntityStatus.ENABLED, EntityStatus.ENABLED);
    }

    @Test
    public void findAllByStatusWithArgumentDisabledReturnsOnlyDisabledCourses() {
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseEnabled(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseEnabled(department));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourseDisabled(department));

        List<Course> disabledCourses = courseRepository.findAllByStatus(EntityStatus.DISABLED);

        assertThat(disabledCourses)
                .hasSize(1)
                .extracting(Course::getStatus)
                .containsExactlyInAnyOrder(EntityStatus.DISABLED);
    }

    @Test
    public void findAllByStatusWithoutPersistedCoursesReturnsAnEmptyList() {
        List<Course> emptyCourseList = courseRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(emptyCourseList)
            .isEmpty();
    }

    @Test
    public void findAllByDepartmentIdWithExistentDepartmentIdReturnsCourseList() {
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department2 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department1);
        entityManager.persistAndFlush(department2);
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department2));

        List<Course> coursesWithTheSameDepartmentId = courseRepository.findAllByDepartmentId(department1.getId());

        assertThat(coursesWithTheSameDepartmentId)
                .hasSize(2)
                .extracting(Course::getDepartment)
                .containsExactlyInAnyOrder(department1, department1);
    }

    @Test
    public void findAllByDepartmentIdWithoutPersistedCoursesReturnsEmptyList() {
        UUID sampleUUID = UUID.randomUUID();

        List<Course> emptyCourseList = courseRepository.findAllByDepartmentId(sampleUUID);

        assertThat(emptyCourseList)
                .isEmpty();
    }

    @Test
    public void findAllByDepartmentIdWithNewDepartmentIdReturnsEmptyList() {
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department2 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department1);
        entityManager.persistAndFlush(department2);
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department2));

        UUID newDepartmentId = UUID.randomUUID();

        List<Course> emptyCourseList = courseRepository.findAllByDepartmentId(newDepartmentId);

        assertThat(emptyCourseList)
                .isEmpty();
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdWithExistentCourseReturnsTrue() {
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department2 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department1);
        entityManager.persistAndFlush(department2);
        Course course1 = CourseFactoryUtils.sampleCourse(department1);
        Course course2 = CourseFactoryUtils.sampleCourse(department1);
        Course course3 = CourseFactoryUtils.sampleCourse(department2);

        entityManager.persistAndFlush(course1);
        entityManager.persistAndFlush(course2);
        entityManager.persistAndFlush(course3);

        Boolean courseExists = courseRepository.existsByAbbreviationAndDepartmentId(course1.getAbbreviation(), course1.getDepartment().getId());

        assertThat(courseExists).isTrue();
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdWithoutExistentCourseReturnsFalse() {
        Department department1 = DepartmentFactoryUtils.sampleDepartment(campus);
        Department department2 = DepartmentFactoryUtils.sampleDepartment(campus);
        entityManager.persistAndFlush(department1);
        entityManager.persistAndFlush(department2);
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department1));
        entityManager.persistAndFlush(CourseFactoryUtils.sampleCourse(department2));

        String courseAbbreviation = "XY";
        UUID departmentId = UUID.randomUUID();

        Boolean courseExists = courseRepository.existsByAbbreviationAndDepartmentId(courseAbbreviation, departmentId);

        assertThat(courseExists).isFalse();
    }

    @Test
    public void existsByAbbreviationAndDepartmentIdWithoutPersistedCoursesReturnsFalse() {
        UUID departmentUUID = department.getId();

        Boolean courseExists = courseRepository.existsByAbbreviationAndDepartmentId("TC", departmentUUID);

        assertThat(courseExists).isFalse();
    }

}