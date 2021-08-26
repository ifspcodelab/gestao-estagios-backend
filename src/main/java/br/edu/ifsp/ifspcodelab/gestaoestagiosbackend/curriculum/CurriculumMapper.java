package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurriculumMapper {
    CurriculumDto to(Curriculum curriculum);
}
