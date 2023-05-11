package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;


import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.UserUpdatePasswordDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Advisor createAdvisor(UserAdvisorCreateDto userAdvisorCreateDto);
    User updateUser(UUID id, UserUpdateDto userUpdateDto);
    Advisor updateAdvisor(UUID id, UserAdvisorUpdateDto userAdvisorUpdateDto);
    List<User> findAll();
    Student createStudent(UserStudentCreateDto userStudentCreateDto);
    boolean existsByEmailExcludedId(String email, UUID id);
    boolean existsByRegistrationExcludeId(String registration, UUID id);
    Optional<User> findById(UUID id);
    User findByRegistrationIgnoreCase(String registration);
    void save(User user);
    void delete(UUID id);
    void activateAdvisor(UUID idAdvisor, UserUpdatePasswordDto userUpdatePasswordDto);
    void activateStudent(UUID idStudent);
    User sendMailPassword(String registration);
    void changePassword(UUID id, UserUpdatePasswordDto userUpdatePasswordDto);
}
