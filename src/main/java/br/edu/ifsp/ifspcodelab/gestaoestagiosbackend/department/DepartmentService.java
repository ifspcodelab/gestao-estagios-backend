package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import java.util.UUID;

public interface DepartmentService {
    Department create(DepartmentCreateDto departmentCreateDto);
}
