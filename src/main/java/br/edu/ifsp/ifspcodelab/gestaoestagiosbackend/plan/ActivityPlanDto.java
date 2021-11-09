package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.plan;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestForStudentDto;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class ActivityPlanDto {
    UUID id;
    String companyName;
    Instant internshipStartDate;
    Instant internshipEndDate;
    Instant createdAt;
    Instant expiresAt;
    String activityPlanUrl;
    RequestStatus status;
    String details;
    AdvisorRequestForStudentDto advisorRequest;
}
