package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.SmtpAuthenticator;
import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.request.RequestExpiresVerification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
//@Import(RequestExpiresVerification.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
