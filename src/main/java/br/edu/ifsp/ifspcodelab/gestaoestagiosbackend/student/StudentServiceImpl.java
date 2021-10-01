package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceAlreadyExistsException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.Curriculum;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.User;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserRepository;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.UserStudentCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private CurriculumService curriculumService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

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

        if(userRepository.existsByEmailExcludedId(userDto.getEmail(), userDto.getId())){
            throw new ResourceAlreadyExistsException(ResourceName.STUDENT, userDto.getEmail(), userDto);
        }

        if(userRepository.existsByRegistrationExcludeId(userDto.getRegistration(), userDto.getId())){
            throw new ResourceAlreadyExistsException(ResourceName.STUDENT, userDto.getRegistration(), userDto);
        }

        Optional<User> userUpdate = userRepository.findById(userDto.getId());

        if(userUpdate.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.STUDENT, userDto.getId());
        }

        User user = userUpdate.get();

        user.setRegistration(userDto.getRegistration());
        user.setName(userDto.getName());

        userRepository.save(user);

        return userDto;
    }

    @Override
    public void delete(UUID id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isEmpty()){
            throw new ResourceNotFoundException(ResourceName.STUDENT, id);
        }

        studentRepository.deleteById(id);
        userRepository.deleteById(student.get().getUser().getId());
    }
}
