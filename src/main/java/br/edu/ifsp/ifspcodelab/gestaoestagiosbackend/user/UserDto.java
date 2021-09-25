package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import lombok.Value;

import java.util.UUID;

@Value
public class UserDto {
    UUID id;
    String registration;
}
