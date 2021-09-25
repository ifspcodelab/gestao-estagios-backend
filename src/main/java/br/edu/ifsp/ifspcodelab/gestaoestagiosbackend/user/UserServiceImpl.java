package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourceName;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.exceptions.ResourcesNotFoundException;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.course.Course;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.Advisor;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user.advisor.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private AdvisorService advisorService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAdvisorService(AdvisorService advisorService) {
        this.advisorService = advisorService;
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
        List<Course> courses = advisorService.getCourses(userAdvisorCreateDto);

        if(userRepository.existsByEmail(userAdvisorCreateDto.getEmail())) {
            throw new UserAlreadyExistsException(userAdvisorCreateDto.getEmail());
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
}
