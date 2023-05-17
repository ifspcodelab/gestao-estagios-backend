package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceReferentialIntegrityException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.Department;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseRepository courseRepository;

    private DepartmentService departmentService;
    private CurriculumService curriculumService;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Autowired
    public void setDepartmentService(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public Course create(CourseCreateDto courseCreateDto) {
        Department department = departmentService.findById(courseCreateDto.getDepartmentId());

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
        return courseRepository.findAllByOrderByNameAsc();
    }

    @Override
    public List<Course> findAllByStatus(EntityStatus status) {
        return courseRepository.findAllByStatusOrderByNameAsc(status);
    }

    @Override
    public Course findById(UUID courseId) {
        return getCourse(courseId);
    }

    @Override
    public Course update(UUID courseId, CourseCreateDto courseCreateDto) {
        Course course = getCourse(courseId);
        Department department = departmentService.findById(courseCreateDto.getDepartmentId());

        if (courseRepository.existsByAbbreviationAndDepartmentIdExcludedId(
            courseCreateDto.getAbbreviation(),
            courseCreateDto.getDepartmentId(),
            courseId)) {
            throw new CourseAlreadyExistsByAbbreviationAndDepartmentIdException(
                courseCreateDto.getAbbreviation(),
                courseCreateDto.getDepartmentId()
            );
        }

        course.setName(courseCreateDto.getName());
        course.setAbbreviation(courseCreateDto.getAbbreviation());
        course.setNumberOfPeriods(courseCreateDto.getNumberOfPeriods());
        course.setDepartment(department);

        return courseRepository.save(course);
    }

    @Transactional
    @Override
    public Course setStatus(UUID courseId, EntityUpdateStatusDto courseUpdateStatusDto) {
        Course courseUpdated = getCourse(courseId);

        courseUpdated.setStatus(courseUpdateStatusDto.getStatus());

        if (courseUpdated.getStatus().equals(EntityStatus.DISABLED)) {
            curriculumService.disableAllByCourseId(courseId);
        }
        else {
            enable(courseId);
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

    @Transactional
    @Override
    public void disableAllByDepartmentId(UUID departmentId) {
        this.findAllByDepartmentId(departmentId)
            .forEach(course -> curriculumService.disableAllByCourseId(course.getId()));
        courseRepository.disableAllByDepartmentId(departmentId);
    }

    @Override
    public boolean existsByDepartmentId(UUID departmentId) {
        return courseRepository.existsByDepartmentId(departmentId);
    }

    @Override
    public List<Course> findAllByDepartmentId(UUID departmentId) {
        return courseRepository.findAllByDepartmentId(departmentId);
    }

    @Override
    public Course enable(UUID courseId) {
        Course course = getCourse(courseId);
        if (course.getDepartment().getStatus().equals(EntityStatus.DISABLED)) {
            departmentService.enable(
                course.getDepartment().getCampus().getId(),
                course.getDepartment().getId()
            );
        }
        return courseRepository.save(course.enable());
    }

    @Override
    public List<Course> findByIdIn(List<UUID> coursesIds) {
        return courseRepository.findByIdIn(coursesIds);
    }

    private Course getCourse(UUID courseId) {
        return courseRepository.findById(courseId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.COURSE, courseId));
    }
}
