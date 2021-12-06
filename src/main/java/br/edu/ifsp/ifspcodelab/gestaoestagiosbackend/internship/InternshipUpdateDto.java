package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.internship;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.InternshipStatus;
import lombok.Value;

@Value
public class InternshipUpdateDto {
    InternshipStatus status;
}
