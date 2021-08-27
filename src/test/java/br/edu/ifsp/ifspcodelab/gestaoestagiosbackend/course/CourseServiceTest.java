//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;
//
//import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class CourseServiceTest {
//    @Mock
//    private DepartmentRepository departmentRepository;
//    @Mock
//    private CourseRepository courseRepository;
//    @InjectMocks
//    private CourseServiceImpl courseService;
//
//    private Course course;
//
//    @BeforeEach
//    public void setUp() {
//        course = CourseFactoryUtils.sampleCourse();
//    }
//
//    @Test
//    public void createCourse() {
//        when(departmentRepository.findById(any(UUID.class))).thenReturn(
//            Optional.of(CourseFactoryUtils.sampleCourse().getDepartment())
//        );
//        when(courseRepository.save(any(Course.class))).thenReturn(CourseFactoryUtils.sampleCourse());
//
//        Course courseCreated = courseService.create(
//            new CourseCreateDto(
//                course.getName(),
//                course.getAbbreviation(),
//                course.getNumberOfPeriods(),
//                course.getDepartment().getId()
//            )
//        );
//
//        verify(courseRepository, times(1)).save(any(Course.class));
//
//        assertThat(courseCreated).isNotNull();
//        assertThat(courseCreated.getId()).isNotNull();
//        assertThat(courseCreated.getName()).isEqualTo(course.getName());
//        assertThat(courseCreated.getAbbreviation()).isEqualTo(course.getAbbreviation());
//        assertThat(courseCreated.getNumberOfPeriods()).isEqualTo(course.getNumberOfPeriods());
//        assertThat(courseCreated.getDepartment().getName()).isEqualTo(course.getDepartment().getName());
//        assertThat(courseCreated.getDepartment().getAbbreviation()).isEqualTo(course.getDepartment().getAbbreviation());
//    }
//
//    @Test
//    public void createCourseAlreadyExistsByAbbreviationAndDepartmentId() {
//        when(departmentRepository.findById(any(UUID.class))).thenReturn(
//            Optional.of(CourseFactoryUtils.sampleCourse().getDepartment())
//        );
//        when(courseRepository.existsByAbbreviationAndDepartmentId(
//            course.getAbbreviation(),
//            course.getDepartment().getId()
//        )).thenReturn(true);
//
//        assertThatThrownBy(() -> courseService.create(
//            new CourseCreateDto(
//                course.getName(),
//                course.getAbbreviation(),
//                course.getNumberOfPeriods(),
//                course.getDepartment().getId()
//            )
//        )).isInstanceOf(CourseAlreadyExistsByAbbreviationAndDepartmentIdException.class);
//    }
//}
