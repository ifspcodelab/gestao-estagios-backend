package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDtoSimplified;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class AdvisorDtoSimplified {
    UUID id;
    UserDtoSimplified user;
    Set<CourseDto> courses;
}
