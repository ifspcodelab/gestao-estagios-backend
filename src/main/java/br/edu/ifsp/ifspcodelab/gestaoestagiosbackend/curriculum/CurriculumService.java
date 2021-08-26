package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;

import java.util.List;
import java.util.UUID;

public interface CurriculumService {
    Curriculum create(UUID courseId, CurriculumCreateDto curriculumCreateDto);
    List<Curriculum> findAll(UUID courseId);
    Curriculum findById(UUID courseId, UUID curriculumId);
    Curriculum update(UUID courseId, UUID curriculumId, CurriculumCreateDto curriculumCreateDto);
    Curriculum setStatus(UUID courseId, UUID curriculumId, EntityUpdateStatusDto curriculumUpdateStatusDto);
    void delete(UUID courseId, UUID curriculumId);
}
