package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvisorMapper {
    AdvisorDto to(Advisor advisor);
}
