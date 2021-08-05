package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentNotExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Course create(CourseCreateDto courseCreateDto) {
        Department department = departmentRepository
                .findById(courseCreateDto.getDepartmentId())
                .orElseThrow(() -> new DepartmentNotExistsException(courseCreateDto.getDepartmentId()));

        if (courseRepository.existsByNameAndDepartmentId(courseCreateDto.getName(), courseCreateDto.getDepartmentId())) {
            throw new CourseExistsForDepartmentException(courseCreateDto.getDepartmentId());
        }

        return this.courseRepository.save (
                new Course(
                    courseCreateDto.getName(),
                    courseCreateDto.getAbbreviation(),
                    courseCreateDto.getNumberOfPeriods(),
                    department
                )
        );
    }
}
