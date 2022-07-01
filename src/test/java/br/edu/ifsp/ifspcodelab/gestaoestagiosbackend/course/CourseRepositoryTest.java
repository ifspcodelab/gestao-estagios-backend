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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    Course course;
    Course enabledCourse;
    Course disabledCourse;

    @BeforeEach
    public void setUp() {
        State state = entityManager.persistAndFlush(StateFactoryUtils.sampleState());
        City city = entityManager.persistAndFlush(CityFactoryUtils.sampleCity(state));
        Campus campus = entityManager.persistAndFlush(CampusFactoryUtils.sampleCampus(city));
        Department department = entityManager.persistAndFlush(DepartmentFactoryUtils.sampleDepartment(campus));
        course = CourseFactoryUtils.sampleCourse(department);
        entityManager.persistAndFlush(course);
        enabledCourse = CourseFactoryUtils.sampleCourse(department);
        enabledCourse.setStatus(EntityStatus.ENABLED);
        entityManager.persistAndFlush(enabledCourse);
        disabledCourse = CourseFactoryUtils.sampleCourse(department);
        disabledCourse.setStatus(EntityStatus.DISABLED);
        entityManager.persistAndFlush(disabledCourse);
    }

    @Test
    public void saveNewCourse() {
        Course courseFound = entityManager.find(Course.class, course.getId());

        assertThat(courseFound).isNotNull();
        assertThat(courseFound.getId()).isEqualTo(course.getId());
        assertThat(courseFound.getName()).isEqualTo(course.getName());
        assertThat(courseFound.getAbbreviation()).isEqualTo(course.getAbbreviation());
        assertThat(courseFound.getNumberOfPeriods()).isEqualTo(course.getNumberOfPeriods());
        assertThat(courseFound.getDepartment()).isEqualTo(course.getDepartment());
    }

    @Test
    public void findAllByStatusWithArgumentEnabledReturnsOnlyEnabledCourses() {

        List<Course> enabledCourses = courseRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(enabledCourses)
                .isNotEmpty()
                .isInstanceOf(ArrayList.class)
                .doesNotContain(disabledCourse);
        

    }

    @Test
    public void findAllByStatusWithArgumentDisabledReturnsOnlyDisabledCourses() {

        List<Course> disabledCourses = courseRepository.findAllByStatus(EntityStatus.DISABLED);

        assertThat(disabledCourses)
                .isNotEmpty()
                .isInstanceOf(ArrayList.class)
                .doesNotContain(enabledCourse);

    }

    @Test
    public void findAllByStatusWithoutCoursesReturnsAnEmptyList() {

        entityManager.remove(course);
        entityManager.remove(enabledCourse);
        entityManager.remove(disabledCourse);

        List<Course> emptyCourseList = courseRepository.findAllByStatus(EntityStatus.ENABLED);

        assertThat(emptyCourseList)
                .isInstanceOf(ArrayList.class)
                .isEmpty();

    }

    @Test
    public void findAllByDepartmentIdWithDepartmentIdReturnsCourseListWithTheSameDepartmentId() {

        UUID departmentId = course.getDepartment().getId();

        List<Course> coursesWithTheSameDepartmentId = courseRepository.findAllByDepartmentId(departmentId);

        assertThat(coursesWithTheSameDepartmentId)
                .isNotEmpty()
                .isInstanceOf(ArrayList.class)
                .hasSize(3);

        assertThat(coursesWithTheSameDepartmentId.get(0).getDepartment().getId()).isEqualTo(departmentId);
        assertThat(coursesWithTheSameDepartmentId.get(1).getDepartment().getId()).isEqualTo(departmentId);
        assertThat(coursesWithTheSameDepartmentId.get(2).getDepartment().getId()).isEqualTo(departmentId);

    }
    @Test
    public void findAllByDepartmentIdWithoutCoursesReturnsEmptyList() {

        entityManager.remove(course);
        entityManager.remove(enabledCourse);
        entityManager.remove(disabledCourse);

        UUID sampleUuid = UUID.randomUUID();

        List<Course> emptyCourseList = courseRepository.findAllByDepartmentId(sampleUuid);

        assertThat(emptyCourseList)
                .isInstanceOf(ArrayList.class)
                .isEmpty();

    }

    @Test
    public void findAllByDepartmentIdWithNewIdReturnsEmptyList() {

        UUID sampleUuid = UUID.randomUUID();

        List<Course> emptyCourseList = courseRepository.findAllByDepartmentId(sampleUuid);

        assertThat(emptyCourseList)
                .isInstanceOf(ArrayList.class)
                .isEmpty();

    }

}