package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.AdvisorRequestDto;
import lombok.Value;

import java.time.Instant;
import java.util.UUID;

@Value
public class RequestAppraisalDto {
    UUID id;
    Instant createdAt;
    String details;
    Boolean isDeferred;
    Instant meetingDate;
    AdvisorRequestDto advisorRequest;
}
