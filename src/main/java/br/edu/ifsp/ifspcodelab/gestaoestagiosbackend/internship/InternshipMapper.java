package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    InternshipDto to(Internship internship);
}
