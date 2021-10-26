package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.appraisal;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.annotations.Optional;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Value
public class RequestAppraisalCreateDto {
    @NotNull
    @NotBlank
    String details;
    Boolean isDeferred;
    Instant meetingDate;
    Boolean sendEmailToAdvisor;
}
