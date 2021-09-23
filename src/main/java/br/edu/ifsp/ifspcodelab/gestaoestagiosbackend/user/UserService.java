package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import java.util.List;

public interface UserService {
    User create(UserCreateDto userCreateDto);
    List<User> findAll();
}
