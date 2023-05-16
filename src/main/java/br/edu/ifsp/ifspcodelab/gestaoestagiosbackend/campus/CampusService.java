package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface CampusService {
    Campus create(CampusCreateDto campusCreateDto);
    List<Campus> findAll(Sort sortOrder);
    List<Campus> findAllByStatus(EntityStatus status);
    Campus findById(UUID id);
    Campus update(UUID id, CampusCreateDto campusCreateDto);
    Campus setStatus(UUID id, EntityUpdateStatusDto campusUpdateStatusDto);
    void delete(UUID id);
    Campus enable(UUID id);
}
