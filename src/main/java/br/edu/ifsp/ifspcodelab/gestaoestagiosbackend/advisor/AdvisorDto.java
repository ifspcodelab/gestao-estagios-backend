package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.CourseDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
public class AdvisorDto {
    UUID id;
    UserDto user;
    Set<CourseDto> courses;
}
