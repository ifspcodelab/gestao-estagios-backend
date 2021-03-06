package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;

import java.util.List;
import java.util.UUID;

public interface CurriculumService {
    Curriculum create(UUID courseId, CurriculumCreateDto curriculumCreateDto);
    List<Curriculum> findAll(UUID courseId);
    List<Curriculum> findByStatus(UUID courseId, EntityStatus status);
    Curriculum findById(UUID courseId, UUID curriculumId);
    Curriculum findByCurriculumId(UUID curriculumId);
    Curriculum update(UUID courseId, UUID curriculumId, CurriculumCreateDto curriculumCreateDto);
    Curriculum setStatus(UUID courseId, UUID curriculumId, EntityUpdateStatusDto curriculumUpdateStatusDto);
    void disableAllByCourseId(UUID courseId);
    boolean existsByCourseId(UUID courseId);
    void delete(UUID courseId, UUID curriculumId);
    Curriculum enable(UUID courseId, UUID curriculumId);
}
