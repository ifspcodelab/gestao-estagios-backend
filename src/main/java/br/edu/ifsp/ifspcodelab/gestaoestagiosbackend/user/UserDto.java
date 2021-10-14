package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import lombok.Value;

import java.util.Collection;
import java.util.UUID;

@Value
public class UserDto {
    UUID id;
    String registration;
    String name;
    String password;
    String email;
    Collection<Role> roles;
    EntityStatus isActivated;
}
