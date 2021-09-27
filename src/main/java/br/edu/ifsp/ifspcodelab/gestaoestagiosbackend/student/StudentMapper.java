package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto to(Student student);
}
