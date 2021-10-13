package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MailConfig.class)
@ConfigurationProperties(prefix = "application.mail")
@NoArgsConstructor
@Getter
@Setter
public class MailConfig {
    private String server;
    private String port;
    private String username;
    private String password;
}
