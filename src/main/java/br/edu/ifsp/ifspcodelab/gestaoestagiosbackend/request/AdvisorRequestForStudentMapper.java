package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdvisorRequestForStudentMapper {
    AdvisorRequestForStudentDto to(AdvisorRequest advisorRequest);
}
