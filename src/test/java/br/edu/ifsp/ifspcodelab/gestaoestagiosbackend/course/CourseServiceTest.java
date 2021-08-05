package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentNotExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @InjectMocks
    private CourseServiceImpl courseService;

    private String name;
    private String abbreviation;
    private Integer numberOfPeriods;
    private Department department;

    @BeforeEach
    public void setUp() {
        name = "Test Course";
        abbreviation = "TC";
        numberOfPeriods = 6;
        department = new Department("Test Department");
    }

    @Test
    public void createCourseDepartmentNotExists() {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> courseService.create(
                new CourseCreateDto(name, abbreviation, numberOfPeriods, department.getId())))
                .isInstanceOf(DepartmentNotExistsException.class)
                .hasFieldOrPropertyWithValue("departmentId", department.getId());
    }

    @Test
    public void createCourseAlreadyExistsByNameAndDepartmentId() {
        when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));
        when(courseRepository.existsByNameAndDepartmentId(name, department.getId())).thenReturn(true);

        assertThatThrownBy(() -> courseService.create(
                new CourseCreateDto(name, abbreviation, numberOfPeriods, department.getId())))
                .isInstanceOf(CourseExistsForDepartmentException.class)
                .hasFieldOrPropertyWithValue("departmentId", department.getId());
    }

}
