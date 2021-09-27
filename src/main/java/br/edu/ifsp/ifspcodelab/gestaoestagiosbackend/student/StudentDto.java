package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;
import lombok.Value;

import java.util.UUID;

@Value
public class StudentDto {
    UUID id;
    UserDto user;
}
