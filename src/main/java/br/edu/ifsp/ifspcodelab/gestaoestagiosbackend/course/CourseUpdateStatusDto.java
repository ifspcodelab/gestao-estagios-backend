package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
public class CourseUpdateStatusDto {
    @NotNull
    EntityStatus status;
    @NotNull
    UUID departmentId;
}
