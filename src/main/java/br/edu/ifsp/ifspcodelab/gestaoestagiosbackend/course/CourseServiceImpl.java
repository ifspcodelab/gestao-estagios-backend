package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private DepartmentRepository departmentRepository;
    private CourseRepository courseRepository;

    @Override
    public Course create(CourseCreateDto courseCreateDto) {
        if (courseRepository.existsByAbbreviationAndDepartmentId(
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getDepartmentId())) {
            throw new CourseAlreadyExistsByAbbreviationAndDepartmentIdException(
                courseCreateDto.getAbbreviation(),
                courseCreateDto.getDepartmentId()
            );
        }

        Department department = departmentRepository
            .findById(courseCreateDto.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException(
                ResourceName.DEPARTMENT,
                courseCreateDto.getDepartmentId()
            ));

        return courseRepository.save(new Course(
            courseCreateDto.getName(),
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getNumberOfPeriods(),
            department
        ));
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Course findById(UUID courseId) {
        return null;
    }

    @Override
    public Course update(UUID courseId, CourseCreateDto courseCreateDto) {
        return null;
    }

    @Override
    public void delete(UUID courseId) {

    }
}
