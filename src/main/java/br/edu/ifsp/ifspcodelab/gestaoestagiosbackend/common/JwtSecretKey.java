package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common;

import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtConfig.class)
@AllArgsConstructor
public class JwtSecretKey {

    private final JwtConfig jwtConfig;

    @Bean
    public Algorithm secretKey() {
        return Algorithm.HMAC256(jwtConfig.getSecretKey().getBytes());
    }
}
