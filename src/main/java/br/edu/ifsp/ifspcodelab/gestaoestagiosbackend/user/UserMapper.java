package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto to(User user);
}
