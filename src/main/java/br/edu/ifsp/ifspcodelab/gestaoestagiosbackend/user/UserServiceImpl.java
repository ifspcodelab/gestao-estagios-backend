package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.dtos.UserUpdatePasswordDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.EntityStatus;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.enums.Role;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourcesNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SenderMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.CreatorParametersMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config.FormatterMail;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount.CreateAccountHtml;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.advisor.AdvisorService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.curriculum.CurriculumService;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.Student;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Value("${application.frontend.url}")
    private String baseUrl;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SenderMail senderMail;

    private AdvisorService advisorService;
    private StudentService studentService;

    private CurriculumService curriculumService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, SenderMail senderMail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.senderMail = senderMail;
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
        User user = this.findByRegistration(registration);
        return user;
    }


    @Override
    public Advisor createAdvisor(UserAdvisorCreateDto userAdvisorCreateDto) {
        Set<Course> courses = advisorService.getCourses(userAdvisorCreateDto.getCoursesIds());

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
            userAdvisorCreateDto.getEmail(),
            userAdvisorCreateDto.getRoles()
        );
        User user = userRepository.save(userCreated);

        Advisor advisor = advisorService.create(new Advisor(userCreated, courses));

        MailDto email = MailDto.builder()
                .title("Bem vindo(a)!!")
                .msgHTML(CreateAccountHtml.getMessageHtml())
                .build();

        Map<String, String> params = CreatorParametersMail.setParameters(userAdvisorCreateDto.getName(), baseUrl+"/authentication/reset", advisor.getId());
        email = FormatterMail.build(email, params);

        email.setRecipientTo(user.getEmail());

        senderMail.sendEmail(email);

        return advisor;
    }

    @Override
    public User updateUser(UUID id, UserUpdateDto userUpdateDto) {
        User user = this.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, id));

        user.setName(userUpdateDto.getName());

        return userRepository.save(user);
    }

    @Override
    public Advisor updateAdvisor(UUID id, UserAdvisorUpdateDto userAdvisorUpdateDto) {
        Advisor advisor = advisorService.findById(id);
        Set<Course> courses = advisorService.getCourses(userAdvisorUpdateDto.getCoursesIds());

        if (userRepository.existsByEmailExcludedId(userAdvisorUpdateDto.getEmail(), advisor.getUser().getId())) {
            throw new UserAlreadyExistsByEmailException(userAdvisorUpdateDto.getEmail());
        }
        if (courses.size() == 0) {
            throw new ResourcesNotFoundException(ResourceName.COURSE, userAdvisorUpdateDto.getCoursesIds());
        }

        Advisor advisorUpdated = new Advisor(advisor.getUser(), new HashSet<>(courses));
        advisorUpdated.setId(id);

        User userUpdated = advisorUpdated.getUser();
        userUpdated.setName(userAdvisorUpdateDto.getName());
        userUpdated.setEmail(userAdvisorUpdateDto.getEmail());

        userRepository.save(userUpdated);
        return advisorService.create(advisorUpdated);
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

        User user = userRepository.save(userCreated);
        Student student = studentService.create(
            new Student(userCreated, curriculumService.findByCurriculumId(userStudentCreateDto.getCurriculumId())));

        MailDto email = MailDto.builder()
                .title("Bem vindo(a)!!")
                .msgHTML(CreateAccountHtml.getMessageHtml())
                .build();

        Map<String, String> params = CreatorParametersMail.setParameters(userStudentCreateDto.getName(), baseUrl+"/authentication/verification", student.getId());
        email = FormatterMail.build(email, params);

        email.setRecipientTo(user.getEmail());

        senderMail.sendEmail(email);

        return student;
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
    public User findByRegistration(String registration) {
        return userRepository.findByRegistration(registration)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with registration " + registration));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void activateAdvisor(UUID idAdvisor, UserUpdatePasswordDto userUpdatePasswordDto) {
        Advisor advisor = advisorService.findById(idAdvisor);

        advisor.getUser().setIsActivated(EntityStatus.ENABLED);
        advisor.getUser().setPassword(passwordEncoder.encode(userUpdatePasswordDto.getPassword()));

        userRepository.save(advisor.getUser());
    }

    @Override
    public void activateStudent(UUID idStudent) {
        Student student = studentService.findById(idStudent);

        student.getUser().setIsActivated(EntityStatus.ENABLED);

        userRepository.save(student.getUser());
    }

    @Override
    public User sendMailPassword(String registration) {
        User user = findByRegistration(registration);

        MailDto email = MailDto.builder()
            .title("Redefinição de senha")
            .msgHTML(CreateAccountHtml.getPasswordResetHtml())
            .build();

        Map<String, String> params = CreatorParametersMail.setParameters(
            user.getName(),
            baseUrl+"/authentication/reset-password",
            user.getId()
        );
        email = FormatterMail.build(email, params);
        email.setRecipientTo(user.getEmail());
        senderMail.sendEmail(email);
        return user;
    }

    @Override
    public void changePassword(UUID id, UserUpdatePasswordDto userUpdatePasswordDto) {
        User user = findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ResourceName.USER, id));

        user.setPassword(passwordEncoder.encode(userUpdatePasswordDto.getPassword()));
        userRepository.save(user);
    }
}
