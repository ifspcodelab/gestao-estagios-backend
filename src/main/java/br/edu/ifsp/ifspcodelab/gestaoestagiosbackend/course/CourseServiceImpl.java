package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    private DepartmentRepository departmentRepository;
    private CourseRepository courseRepository;

    private CurriculumService curriculumService;

    public CourseServiceImpl(DepartmentRepository departmentRepository, CourseRepository courseRepository) {
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Override
    public Course create(CourseCreateDto courseCreateDto) {
        Department department = getDepartment(courseCreateDto);

        if (department.getStatus().equals(EntityStatus.DISABLED)) {
            throw new ResourceReferentialIntegrityException(ResourceName.COURSE, ResourceName.DEPARTMENT);
        }

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

    @Transactional
    @Override
    public Course setStatus(UUID courseId, EntityUpdateStatusDto courseUpdateStatusDto) {
        Course courseUpdated = getCourse(courseId);

        courseUpdated.setStatus(courseUpdateStatusDto.getStatus());

        if (courseUpdated.getStatus().equals(EntityStatus.DISABLED)) {
            curriculumService.disableAllByCourseId(courseId);
        }
        return courseRepository.save(courseUpdated);
    }

    @Override
    public void delete(UUID courseId) {
        getCourse(courseId);
        if (curriculumService.existsByCourseId(courseId)) {
            throw new ResourceReferentialIntegrityException(ResourceName.COURSE, ResourceName.CURRICULUM);
        }
        courseRepository.deleteById(courseId);
    }

    @Override
    public void disableAllByDepartmentId(UUID departmentId) {
        courseRepository.disableAllByDepartmentId(departmentId);
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
