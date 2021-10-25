package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.RequestStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentDtoSimplified;
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
    private RequestStatus status;
    private StudentDtoSimplified student;
    private CurriculumDto curriculum;
}
