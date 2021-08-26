package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CurriculumServiceImpl implements CurriculumService {
    private CurriculumRepository curriculumRepository;
    private CourseService courseService;

    public CurriculumServiceImpl(CurriculumRepository curriculumRepository) {
        this.curriculumRepository = curriculumRepository;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public Curriculum create(UUID courseId, CurriculumCreateDto curriculumCreateDto) {
        Course course = courseService.findById(courseId);

        return curriculumRepository.save(new Curriculum(
            curriculumCreateDto.getCode(),
            curriculumCreateDto.getCourseLoad(),
            curriculumCreateDto.getInternshipCourseLoad(),
            curriculumCreateDto.getInternshipStartCriteria(),
            curriculumCreateDto.getInternshipAllowedActivities(),
            course
        ));
    }

    @Override
    public List<Curriculum> findAll(UUID courseId) {
        courseService.findById(courseId);
        return curriculumRepository.findAll();
    }

    @Override
    public Curriculum findById(UUID courseId, UUID curriculumId) {
        courseService.findById(courseId);
        return getCurriculum(curriculumId);
    }

    @Override
    public Curriculum update(UUID courseId, UUID curriculumId, CurriculumCreateDto curriculumCreateDto) {
        return null;
    }

    @Override
    public Curriculum setStatus(UUID courseId, UUID curriculumId, EntityUpdateStatusDto curriculumUpdateStatusDto) {
        return null;
    }

    @Override
    public void delete(UUID courseId, UUID curriculumId) {
        courseService.findById(courseId);
        getCurriculum(curriculumId);
        curriculumRepository.deleteById(curriculumId);
    }

    private Curriculum getCurriculum(UUID curriculumId) {
        return curriculumRepository.findById(curriculumId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CURRICULUM, curriculumId));
    }
}
