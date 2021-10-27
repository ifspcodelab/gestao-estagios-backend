package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RequestAppraisalMapper {
    RequestAppraisalDto to(RequestAppraisal requestAppraisal);
}
