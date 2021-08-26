package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.EntityUpdateStatusDto;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department create(UUID campusId, DepartmentCreateDto departmentCreateDto);
    List<Department> findAll(UUID campusId);
    Department findById(UUID campusId, UUID departmentId);
    Department update(UUID campusId, UUID id, DepartmentCreateDto departmentCreateDto);
    Department setStatus(UUID campusId, UUID id, EntityUpdateStatusDto departmentUpdateStatusDto);
    void delete(UUID campusId, UUID departmentId);
}
