package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AdvisorRequestForAdvisorDto {
    private UUID id;
    private Instant createdAt;
    private Instant expiresAt;
    private InternshipType internshipType;
    private String details;
    private RequestStatus requestStatus;
    private String curriculumCode;
    private String studentName;
    private String studentRegistration;
    private String studentCourseName;
    private UUID studentId;
    private UUID advisorId;
    private UUID curriculumId;
}
