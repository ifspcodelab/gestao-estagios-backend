package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return curriculumRepository.findAllByCourseId(courseId);
    }

    @Override
    public Curriculum findById(UUID courseId, UUID curriculumId) {
        courseService.findById(courseId);
        return getCurriculum(courseId, curriculumId);
    }

    @Override
    public List<Curriculum> findByStatus(UUID courseId, EntityStatus status){
        courseService.findById(courseId);
        return curriculumRepository.findByCourseIdAndStatus(courseId, status);
    }

    @Override
    public Curriculum findByCurriculumId(UUID curriculumId) {
        return curriculumRepository.findById(curriculumId).orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.CURRICULUM, curriculumId));
    }

    @Override
    public Curriculum update(UUID courseId, UUID curriculumId, CurriculumCreateDto curriculumCreateDto) {
        Course course = courseService.findById(courseId);
        getCurriculum(courseId, curriculumId);

        Curriculum curriculumUpdated = new Curriculum(
            curriculumCreateDto.getCode(),
            curriculumCreateDto.getCourseLoad(),
            curriculumCreateDto.getInternshipCourseLoad(),
            curriculumCreateDto.getInternshipStartCriteria(),
            curriculumCreateDto.getInternshipAllowedActivities(),
            course
        );
        curriculumUpdated.setId(curriculumId);
        return curriculumRepository.save(curriculumUpdated);
    }

    @Transactional
    @Override
    public Curriculum setStatus(UUID courseId, UUID curriculumId, EntityUpdateStatusDto curriculumUpdateStatusDto) {
        courseService.findById(courseId);
        Curriculum curriculumUpdated = getCurriculum(courseId, curriculumId);

        curriculumUpdated.setStatus(curriculumUpdateStatusDto.getStatus());

        if (curriculumUpdated.getStatus().equals(EntityStatus.ENABLED)) {
            enable(courseId, curriculumId);
        }

        return curriculumRepository.save(curriculumUpdated);
    }

    @Transactional
    @Override
    public void disableAllByCourseId(UUID courseId) {
        curriculumRepository.disableAllByCourseId(courseId);
    }

    @Override
    public boolean existsByCourseId(UUID courseId) {
        return curriculumRepository.existsByCourseId(courseId);
    }

    @Override
    public void delete(UUID courseId, UUID curriculumId) {
        courseService.findById(courseId);
        getCurriculum(courseId, curriculumId);
        curriculumRepository.deleteById(curriculumId);
    }

    @Override
    public Curriculum enable(UUID courseId, UUID curriculumId) {
        Curriculum curriculum = getCurriculum(courseId, curriculumId);
        if (curriculum.getCourse().getStatus().equals(EntityStatus.DISABLED)) {
            courseService.enable(courseId);
        }
        return curriculumRepository.save(curriculum.enable());
    }

    private Curriculum getCurriculum(UUID courseId, UUID curriculumId) {
        return curriculumRepository.findAllByCourseIdAndId(courseId, curriculumId)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.CURRICULUM, curriculumId));
    }
}
