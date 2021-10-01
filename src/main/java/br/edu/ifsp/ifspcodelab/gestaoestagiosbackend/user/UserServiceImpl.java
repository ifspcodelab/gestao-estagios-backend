package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourcesNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private AdvisorService advisorService;
    private StudentService studentService;

    private CurriculumService curriculumService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdvisorService(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @Autowired
    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Autowired
    public void setCurriculumService(CurriculumService curriculumService) {
        this.curriculumService = curriculumService;
    }

    @Override
    public UserDetails loadUserByUsername(String registration) throws UsernameNotFoundException {
        User user = userRepository.findByRegistration(registration);
        if (user == null) {
            throw new UsernameNotFoundException("User not found in the database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getRegistration(), user.getPassword(), authorities);
    }


    @Override
    public Advisor createAdvisor(UserAdvisorCreateDto userAdvisorCreateDto) {
        List<Course> courses = advisorService.getCourses(userAdvisorCreateDto.getCoursesIds());

        if (userRepository.existsByRegistration(userAdvisorCreateDto.getRegistration())) {
            throw new UserAlreadyExistsByRegistrationException(userAdvisorCreateDto.getRegistration());
        }
        if(userRepository.existsByEmail(userAdvisorCreateDto.getEmail())) {
            throw new UserAlreadyExistsByEmailException(userAdvisorCreateDto.getEmail());
        }
        if (courses.size() == 0) {
            throw new ResourcesNotFoundException(ResourceName.COURSE, userAdvisorCreateDto.getCoursesIds());
        }

        User userCreated = new User(
            userAdvisorCreateDto.getRegistration(),
            userAdvisorCreateDto.getName(),
            passwordEncoder.encode(userAdvisorCreateDto.getPassword()),
            userAdvisorCreateDto.getEmail(),
            userAdvisorCreateDto.getRoles()
        );
        userRepository.save(userCreated);

        return advisorService.create(new Advisor(userCreated, courses));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Student createStudent(UserStudentCreateDto userStudentCreateDto) {
        if (userRepository.existsByRegistration(userStudentCreateDto.getRegistration())) {
            throw new UserAlreadyExistsByRegistrationException(userStudentCreateDto.getRegistration());
        }
        if(userRepository.existsByEmail(userStudentCreateDto.getEmail())) {
            throw new UserAlreadyExistsByEmailException(userStudentCreateDto.getEmail());
        }

        User userCreated = new User(
                userStudentCreateDto.getRegistration(),
                userStudentCreateDto.getName(),
                passwordEncoder.encode(userStudentCreateDto.getPassword()),
                userStudentCreateDto.getEmail(),
                List.of(Role.ROLE_STUDENT)
        );
        userRepository.save(userCreated);

        return studentService.create(new Student(userCreated, curriculumService.findByCurriculumId(userStudentCreateDto.getCurriculumId())));
    }

    @Override
    public boolean existsByEmailExcludedId(String email, UUID id) {
        return userRepository.existsByEmailExcludedId(email, id);
    }

    @Override
    public boolean existsByRegistrationExcludeId(String registration, UUID id) {
        return userRepository.existsByRegistrationExcludeId(registration, id);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }
}
