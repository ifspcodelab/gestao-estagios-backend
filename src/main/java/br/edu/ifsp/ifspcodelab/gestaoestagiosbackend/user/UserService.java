package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

public interface UserService {
    User create(String email, String password);
}
