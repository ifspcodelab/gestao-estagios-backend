package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;


import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.Advisor;

import java.util.List;

public interface UserService {
    Advisor createAdvisor(UserAdvisorCreateDto userAdvisorCreateDto);
    List<User> findAll();
}
