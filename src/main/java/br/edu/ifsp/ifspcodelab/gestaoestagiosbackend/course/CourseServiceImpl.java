package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {
    private DepartmentRepository departmentRepository;
    private CourseRepository courseRepository;

    @Override
    public Course create(CourseCreateDto courseCreateDto) {
        Department department = getDepartment(courseCreateDto);

        if (courseRepository.existsByAbbreviationAndDepartmentId(
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getDepartmentId())) {
            throw new CourseAlreadyExistsByAbbreviationAndDepartmentIdException(
                courseCreateDto.getAbbreviation(),
                courseCreateDto.getDepartmentId()
            );
        }

        return courseRepository.save(new Course(
            courseCreateDto.getName(),
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getNumberOfPeriods(),
            department
        ));
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(UUID courseId) {
        return getCourse(courseId);
    }

    @Override
    public Course update(UUID courseId, CourseCreateDto courseCreateDto) {
        getCourse(courseId);
        Department department = getDepartment(courseCreateDto);

        if (courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getDepartmentId(),
            courseId)) {
            throw new CourseAlreadyExistsByAbbreviationAndDepartmentIdException(
                courseCreateDto.getAbbreviation(),
                courseCreateDto.getDepartmentId()
            );
        }

        Course courseUpdated = new Course(
            courseCreateDto.getName(),
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getNumberOfPeriods(),
            department
        );
        courseUpdated.setId(courseId);
        return courseRepository.save(courseUpdated);
    }

    @Override
    public Course setStatus(UUID courseId, CourseUpdateStatusDto courseUpdateStatusDto) {
        Course courseUpdated = getCourse(courseId);

        courseUpdated.setStatus(courseUpdateStatusDto.getStatus());

        return courseRepository.save(courseUpdated);
    }

    @Override
    public void delete(UUID courseId) {
        getCourse(courseId);
        courseRepository.deleteById(courseId);
    }

    private Department getDepartment(CourseCreateDto courseCreateDto) {
        return departmentRepository
            .findById(courseCreateDto.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException(
                ResourceName.DEPARTMENT,
                courseCreateDto.getDepartmentId()
            ));
    }

    private Course getCourse(UUID courseId) {
        return courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.COURSE, courseId));
    }
}
