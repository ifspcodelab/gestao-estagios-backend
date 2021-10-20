package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserDtoSimplified {
    UUID id;
    String registration;
    String name;
    String email;
}
