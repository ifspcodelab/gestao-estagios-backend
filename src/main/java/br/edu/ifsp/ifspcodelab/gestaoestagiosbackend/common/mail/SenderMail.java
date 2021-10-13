package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;

@Service
@EnableConfigurationProperties(MailConfig.class)
@Log
@AllArgsConstructor
public class SenderMail {
    private MailConfig mailConfig;

    public boolean sendEmail(MailDto mail) {
        try {
            if (!mail.isValid()) {
                return false;
            }

            Session session = this.config();

            MimeMessage message = this.configMessage(mail, session);
            Transport.send(message);

            log.info("Email enviado com sucesso!");
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "Erro ao enviar email: ".concat(e.getMessage()));
            return false;
        }
    }

    private Session config() {
        var props = new Properties();
        props.put("mail.smtp.host", mailConfig.getServer());
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.port", mailConfig.getPort());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", mailConfig.getPort());
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.checkserveridentity", "true");

        Authenticator authentication = new SmtpAuthenticator(mailConfig.getUsername(), mailConfig.getPassword());
        return Session.getInstance(props, authentication);
    }

    private MimeMessage configMessage(MailDto mail, Session session) throws MessagingException {
        var message = new MimeMessage(session);

        for (String recipient : mail.getRecipientsTO())
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

        if (Objects.nonNull(mail.getRecipientsCC())) {
            for (String recipient : mail.getRecipientsCC())
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
        }

        if (Objects.nonNull(mail.getRecipientsBCC())) {
            for (String recipient : mail.getRecipientsBCC())
                message.addRecipient(Message.RecipientType.BCC, new InternetAddress(recipient));
        }

        message.setSentDate(mail.getDateSent());
        message.setSubject(mail.getTitle());

        var multipart = new MimeMultipart();

        var attachmentText = new MimeBodyPart();
        attachmentText.setContent(mail.getMsgHTML(), "text/html; charset=UTF-8");
        multipart.addBodyPart(attachmentText);

        if (Objects.nonNull(mail.getFiles())) {
            for (File file : mail.getFiles())
                this.setAttachment(multipart, file);
        }

        message.setContent(multipart);

        return message;
    }

    private void setAttachment(Multipart multipart, File file) throws MessagingException {

        try {
            var part = new MimeBodyPart();
            part.setDataHandler(new DataHandler(new FileDataSource(file)));
            part.setFileName(file.getName());
            multipart.addBodyPart(part);

        } catch (MessagingException e) {
            log.log(Level.WARNING, "Erro ao anexar ".concat(file.getName()).concat(" no email."));
        }

    }
}
