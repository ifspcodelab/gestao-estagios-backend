package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    Department create(UUID campusId, DepartmentCreateDto departmentCreateDto);
    List<Department> findAll(UUID campusId);
    Department findById(UUID campusId, UUID departmentId);
    Department update(UUID campusId, UUID id, DepartmentCreateDto departmentCreateDto);
    void delete(UUID campusId, UUID departmentId);
}
