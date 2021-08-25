package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CourseUpdateStatusDto {
    @NotNull
    EntityStatus status;
}
