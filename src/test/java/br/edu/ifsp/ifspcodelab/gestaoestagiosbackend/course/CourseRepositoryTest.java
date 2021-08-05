package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CourseRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void saveNewCourse() {
        Department department = new Department("Test");
        Course course = new Course("Test Course", "TC", 6, department);
        departmentRepository.save(department);
        courseRepository.save(course);
        Course courseFound = entityManager.find(Course.class, course.getId());

        assertThat(courseFound).isNotNull();
        assertThat(courseFound.getId()).isEqualTo(course.getId());
        assertThat(courseFound.getName()).isEqualTo(course.getName());
        assertThat(courseFound.getAbbreviation()).isEqualTo(course.getAbbreviation());
        assertThat(courseFound.getNumberOfPeriods()).isEqualTo(course.getNumberOfPeriods());
        assertThat(courseFound.getDepartment().getId()).isEqualTo(course.getDepartment().getId());
    }

    @Test
    public void courseExistsByNameAndDepartment() {
        Department department = new Department("Test");
        Course course = new Course("Test Course", "TC", 6, department);
        entityManager.persistAndFlush(department);
        entityManager.persistAndFlush(course);
        boolean result = courseRepository.existsByNameAndDepartmentId(course.getName(), department.getId());

        assertThat(result).isTrue();
    }

    @Test
    public void courseNotExistsByNameAndDepartment() {
        Department department = new Department("Test");
        Course course = new Course("Test Course", "TC", 6, department);
        entityManager.persistAndFlush(department);
        entityManager.persistAndFlush(course);
        boolean result = courseRepository.existsByNameAndDepartmentId(course.getName(), UUID.randomUUID());

        assertThat(result).isFalse();
    }
}
