package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Objects;

public class SmtpAuthenticator extends Authenticator {

    private String username;
    private String password;

    public SmtpAuthenticator(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {

        if(!username.isBlank() && Objects.nonNull(username) && !password.isBlank() && Objects.nonNull(password)) {
            return new PasswordAuthentication(username, password);
        }

        return null;
    }
}
