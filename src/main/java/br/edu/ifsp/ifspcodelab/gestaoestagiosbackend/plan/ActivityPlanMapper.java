package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActivityPlanMapper {
    ActivityPlanDto to(ActivityPlan activityPlan);
}
