package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDtoSimplified;
import lombok.Value;

import java.util.UUID;

@Value
public class StudentDtoSimplified {
    UUID id;
    UserDtoSimplified user;
}
