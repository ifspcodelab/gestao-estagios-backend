package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config;

import br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.MailDto;

import java.util.Map;

public abstract class FormatterMail {

    private FormatterMail() {}

    public static MailDto build(MailDto mail, Map<String, String> values) {
        values.forEach((key, value) -> mail.setMsgHTML(mail.getMsgHTML().replace(key, value)));
        return mail;
    }

}
