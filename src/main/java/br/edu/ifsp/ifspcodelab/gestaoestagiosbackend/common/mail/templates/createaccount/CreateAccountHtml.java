package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount;

public abstract class CreateAccountHtml {

    private CreateAccountHtml() {}

    public static String getMessageHtml() {
        return "<!DOCTYPE html>\r\n" +
                "<html lang=\"pt-br\">\r\n" +
                "<head>\r\n" +
                "    <meta charset=\"UTF-8\">\r\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "    <p>Seja bem vindo $USERNAME,</p>\r\n" +
                "    <a href=\"#\">\r\n" +
                "        <p>Clique aqui para ativar sua conta.</p>\r\n" +
                "    </a>\r\n" +
                "</body>\r\n" +
                "</html>";
    }
}
