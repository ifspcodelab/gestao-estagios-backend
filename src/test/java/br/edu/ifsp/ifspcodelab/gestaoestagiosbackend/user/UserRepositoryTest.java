//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class UserRepositoryTest {
//    @Autowired
//    private TestEntityManager entityManager;
//    @Autowired
//    private UserRepository userRepository;
//
//    @Test
//    public void saveNewUser() {
//        User user = new User("email@email.com", "123456");
//        userRepository.save(user);
//        User userFound = entityManager.find(User.class, user.getId());
//
//        assertThat(userFound).isNotNull();
//        assertThat(userFound.getId()).isEqualTo(user.getId());
//        assertThat(userFound.getEmail()).isEqualTo(user.getEmail());
//        assertThat(userFound.getPassword()).isEqualTo(user.getPassword());
//    }
//
//    @Test
//    public void userExists() {
//        User user = new User("email@email.com", "123456");
//        entityManager.persistAndFlush(user);
//        boolean result = userRepository.existsByEmail(user.getEmail());
//
//        assertThat(result).isTrue();
//    }
//
//    @Test
//    public void userNotExists() {
//        entityManager.persistAndFlush(new User("email1@email.com", "123456"));
//        entityManager.persistAndFlush(new User("email2@email.com", "123456"));
//        boolean result = userRepository.existsByEmail("email3@email.com");
//
//        assertThat(result).isFalse();
//    }
//}
