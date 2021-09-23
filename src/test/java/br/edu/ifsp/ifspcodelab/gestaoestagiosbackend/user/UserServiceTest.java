//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @Spy
//    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    private String email;
//    private String password;
//
//    @BeforeEach
//    public void setUp() {
//        email = "john@email.com";
//        password = "123456";
//    }
//
//    @Test
//    public void createUser() {
//        when(userRepository.save(any(User.class))).thenReturn(new User(email, password));
//        when(userRepository.existsByEmail(email)).thenReturn(false);
//
//        User userCreated = userService.create(email, password);
//
//        verify(passwordEncoder, times(1)).encode(password);
//        verify(userRepository, times(1)).existsByEmail(email);
//        verify(userRepository, times(1)).save(any(User.class));
//
//        assertThat(userCreated).isNotNull();
//        assertThat(userCreated.getId()).isNotNull();
//        assertThat(userCreated.getEmail()).isEqualTo(email);
//        assertThat(userCreated.getPassword()).isNotNull();
//    }
//
//    @Test
//    public void createUserAlreadyExists() {
//        when(userRepository.existsByEmail(email)).thenReturn(true);
//
//        assertThatThrownBy(() -> { userService.create(email, password); })
//            .isInstanceOf(UserAlreadyExistsException.class).hasFieldOrPropertyWithValue("email", email);
//
//    }
//}
