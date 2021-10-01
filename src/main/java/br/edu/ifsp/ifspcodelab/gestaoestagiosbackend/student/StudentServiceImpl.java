package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private UserService userService;

    @Autowired
    public UserService getUserService() {
        return userService;
    }

    /*@Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }*/

    //private UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserService userService) {
        this.studentRepository = studentRepository;
        this.userService = userService;
    }

    /*@Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }*/

    @Override
    public Student create(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ResourceName.STUDENT, id));
    }

    @Override
    public UserDto update(UserDto userDto) {

        if(userService.existsByEmailExcludedId(userDto.getEmail(), userDto.getId())){
            throw new ResourceAlreadyExistsException(ResourceName.STUDENT, userDto.getEmail(), userDto);
        }

        if(userService.existsByRegistrationExcludeId(userDto.getRegistration(), userDto.getId())){
            throw new ResourceAlreadyExistsException(ResourceName.STUDENT, userDto.getRegistration(), userDto);
        }

        Optional<User> userUpdate = userService.findById(userDto.getId());

        if(userUpdate.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.STUDENT, userDto.getId());
        }

        User user = userUpdate.get();

        user.setRegistration(userDto.getRegistration());
        user.setName(userDto.getName());

        userService.save(user);

        return userDto;
    }

    @Override
    public void delete(UUID id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.STUDENT, id);
        }

        studentRepository.deleteById(id);
        userService.delete(student.get().getUser().getId());
    }
}
