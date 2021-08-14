package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto to(Department department);
}
