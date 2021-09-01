package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.department;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapperSimplified {
    DepartmentDtoSimplified to(Department department);
}
