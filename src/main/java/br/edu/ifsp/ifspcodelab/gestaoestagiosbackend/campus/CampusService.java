package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.campus;

import java.util.List;
import java.util.UUID;

public interface CampusService {
    Campus create(CampusCreateDto campusCreateDto);
    List<Campus> findAll();
    Campus findById(UUID id);
    Campus update(UUID id, CampusCreateDto campusCreateDto);
    void delete(UUID id);
}
