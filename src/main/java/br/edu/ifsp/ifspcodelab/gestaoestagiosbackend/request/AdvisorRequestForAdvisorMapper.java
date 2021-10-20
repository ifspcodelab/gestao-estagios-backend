package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvisorRequestForAdvisorMapper {
    AdvisorRequestForAdvisorDto to(AdvisorRequest advisorRequest);
}
