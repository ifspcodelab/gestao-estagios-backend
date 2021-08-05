package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ProblemDetail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.Violation;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentNotExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseCreateDto courseCreateDto) {
        Course course = courseService.create(courseCreateDto);
        return new ResponseEntity<>(new CourseDto(course.getId(), course.getName(), course.getAbbreviation(), course.getNumberOfPeriods()), HttpStatus.CREATED);
    }

    @ExceptionHandler(DepartmentNotExistsException.class)
    public ResponseEntity<ProblemDetail> notExists(DepartmentNotExistsException exception) {
        return new ResponseEntity<>(
                new ProblemDetail(
                        "Department not exists",
                        List.of(new Violation("id", "Department does not exists with id " + exception.getDepartmentId()))
                ),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(CourseExistsForDepartmentException.class)
    public ResponseEntity<ProblemDetail> courseExistsForDepartment(CourseExistsForDepartmentException exception) {
        return new ResponseEntity<>(
                new ProblemDetail(
                        "Course already exists for department",
                        List.of(new Violation("id", "Course already exists for department " + exception.getDepartmentId()))
                ),
                HttpStatus.CONFLICT
        );
    }
}
