package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan.ActivityPlan;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestDto;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class InternshipDto {
    UUID id;
    InternshipStatus status;
    AdvisorRequestDto advisorRequest;
    Set<ActivityPlan> activityPlans;
}
