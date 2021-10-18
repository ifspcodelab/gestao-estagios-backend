package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipType;
import lombok.Data;

import java.util.UUID;

@Data
public class AdvisorRequestCreateDto {
    private InternshipType internshipType;
    private String details;
    private UUID studentId;
    private UUID curriculumId;
    private UUID advisorId;
}
