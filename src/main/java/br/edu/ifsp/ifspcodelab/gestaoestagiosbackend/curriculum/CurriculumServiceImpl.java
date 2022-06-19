package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.*;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

        verifyDatesBeforeCreateCurriculum(curriculumCreateDto.getValidityStartDate(),curriculumCreateDto.getValidityEndDate(), courseId);

        return curriculumRepository.save(new Curriculum(
            curriculumCreateDto.getCode(),
            curriculumCreateDto.getCourseLoad(),
            curriculumCreateDto.getInternshipCourseLoad(),
            curriculumCreateDto.getInternshipStartCriteria(),
            curriculumCreateDto.getInternshipAllowedActivities(),
            curriculumCreateDto.getValidityStartDate(),
            curriculumCreateDto.getValidityEndDate(),
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
    public Curriculum findByCurriculumId(UUID curriculumId) {
        return curriculumRepository.findById(curriculumId).orElseThrow(
                () -> new ResourceNotFoundException(ResourceName.CURRICULUM, curriculumId));
    }

    @Override
    public Curriculum update(UUID courseId, UUID curriculumId, CurriculumCreateDto curriculumCreateDto) {
        Course course = courseService.findById(courseId);
        getCurriculum(courseId, curriculumId);

        verifyDatesBeforeUpdateCurriculum(curriculumCreateDto.getValidityStartDate(),curriculumCreateDto.getValidityEndDate(),courseId,curriculumId);

        Curriculum curriculumUpdated = new Curriculum(
            curriculumCreateDto.getCode(),
            curriculumCreateDto.getCourseLoad(),
            curriculumCreateDto.getInternshipCourseLoad(),
            curriculumCreateDto.getInternshipStartCriteria(),
            curriculumCreateDto.getInternshipAllowedActivities(),
            curriculumCreateDto.getValidityStartDate(),
            curriculumCreateDto.getValidityEndDate(),
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


    private void verifyDatesChronology(LocalDate startDate, LocalDate endDate){
        if(endDate.isBefore(startDate)){
            throw new DateChronologyException(startDate, endDate);
        }
    }

    private void verifyDatesBeforeCreateCurriculum(LocalDate startDate, LocalDate endDate, UUID courseId){
        List<Curriculum> existedCurriculums = curriculumRepository.findAllByCourseId(courseId);
        if(endDate != null){
            if(!existedCurriculums.isEmpty()){
                verifyDatesChronology(startDate,endDate);
                for (Curriculum c : existedCurriculums) {
                    if(c.getValidityEndDate() == null){
                        if(startDate.isAfter(c.getValidityStartDate()) || startDate.isEqual(c.getValidityStartDate())
                                || endDate.isAfter(c.getValidityStartDate()) || endDate.isEqual(c.getValidityStartDate())){
                            throw new TimeOverlayException(startDate, endDate, c.getValidityStartDate(), LocalDate.now());
                        }
                    else if(c.getValidityEndDate() != null){
                        if(startDate.isAfter(c.getValidityStartDate()) && startDate.isBefore(c.getValidityEndDate())
                                || endDate.isAfter(c.getValidityStartDate()) && endDate.isBefore(c.getValidityEndDate())
                                || startDate.isEqual(c.getValidityStartDate()) || startDate.isEqual(c.getValidityEndDate())
                                || endDate.isEqual(c.getValidityStartDate()) || endDate.isEqual(c.getValidityEndDate())){
                            throw new TimeOverlayException(startDate, endDate, c.getValidityStartDate(), c.getValidityEndDate());
                            }
                        }
                    }
                }
            }
            else{
                verifyDatesChronology(startDate, endDate);
            }
        }
        else{
            if(!existedCurriculums.isEmpty()){
                for (Curriculum c : existedCurriculums){
                   if(c.getValidityEndDate() == null){
                       throw new TimeOverlayException(startDate, LocalDate.now(), c.getValidityStartDate(), LocalDate.now());
                   }
                   else{
                       if(startDate.isBefore(c.getValidityStartDate()) || startDate.isBefore(c.getValidityEndDate()) || startDate.isEqual(c.getValidityStartDate()) || startDate.isEqual(c.getValidityEndDate())){
                           throw new TimeOverlayException(startDate, LocalDate.now(),c.getValidityStartDate(),c.getValidityEndDate());
                       }
                   }
                }
            }
        }
    }

    private void verifyDatesBeforeUpdateCurriculum(LocalDate startDate, LocalDate endDate, UUID courseId, UUID curriculumId){
        List<Curriculum> existedCurriculums = curriculumRepository.findAllByCourseId(courseId);
        if(endDate != null){
            verifyDatesChronology(startDate,endDate);
            for (Curriculum c : existedCurriculums) {
                if (!curriculumId.equals(c.getId())){
                    if (c.getValidityEndDate() == null) {
                        if (startDate.isAfter(c.getValidityStartDate()) || startDate.isEqual(c.getValidityStartDate())
                                || endDate.isAfter(c.getValidityStartDate()) || endDate.isEqual(c.getValidityStartDate())) {
                            throw new TimeOverlayException(startDate, endDate, c.getValidityStartDate(), LocalDate.now());
                        }
                    }
                    else if (c.getValidityEndDate() != null){
                        if (startDate.isAfter(c.getValidityStartDate()) && startDate.isBefore(c.getValidityEndDate())
                                    || endDate.isAfter(c.getValidityStartDate()) && endDate.isBefore(c.getValidityEndDate())
                                    || startDate.isEqual(c.getValidityStartDate()) || startDate.isEqual(c.getValidityEndDate())
                                    || endDate.isEqual(c.getValidityStartDate()) || endDate.isEqual(c.getValidityEndDate())) {
                            throw new TimeOverlayException(startDate, endDate, c.getValidityStartDate(), c.getValidityEndDate());
                        }
                    }
                }
            }
        }
        else{
            for (Curriculum c : existedCurriculums) {
                if (!curriculumId.equals(c.getId())) {
                    if (c.getValidityEndDate() == null) {
                        throw new TimeOverlayException(startDate, LocalDate.now(), c.getValidityStartDate(), LocalDate.now());
                    }
                    else {
                        if (startDate.isBefore(c.getValidityStartDate()) || startDate.isBefore(c.getValidityEndDate()) || startDate.isEqual(c.getValidityStartDate()) || startDate.isEqual(c.getValidityEndDate())) {
                            throw new TimeOverlayException(startDate, LocalDate.now(), c.getValidityStartDate(), c.getValidityEndDate());
                        }
                    }
                }
            }
        }
    }
}
