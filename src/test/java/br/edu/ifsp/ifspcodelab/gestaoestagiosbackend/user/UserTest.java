//package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.user;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class UserTest {
//
//    @Test
//    public void shouldHaveADefaultConstructorWithoutArgs() {
//        User user = new User();
//        assertThat(user).isNotNull();
//    }
//
//    @Test
//    public void shouldGenerateIdWhenParameterizedConstructorIsCalled() {
//        String email = "email@email.com";
//        String password = "123456";
//        User user = new User(email, password);
//
//        assertThat(user.getId()).isNotNull();
//    }
//
//    @Test
//    public void shouldInitiateAttributesWhenParameterizedConstructorIsCalled() {
//        String email = "email@email.com";
//        String password = "123456";
//        User user = new User(email, password);
//
//        assertThat(user.getId()).isNotNull();
//        assertThat(user.getEmail()).isEqualTo(email);
//        assertThat(user.getPassword()).isEqualTo(password);
//    }
//
//    @Test
//    public void shouldHaveSettersAndGetters() {
//        UUID id = UUID.randomUUID();
//        String email = "email@email.com";
//        String password = "123456";
//        User user = new User();
//
//        user.setId(id);
//        user.setEmail(email);
//        user.setPassword(password);
//
//        assertThat(user.getId()).isEqualTo(id);
//        assertThat(user.getEmail()).isEqualTo(email);
//        assertThat(user.getPassword()).isEqualTo(password);
//    }
//
//}
