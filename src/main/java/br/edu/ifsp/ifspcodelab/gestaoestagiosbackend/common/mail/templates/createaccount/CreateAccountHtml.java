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
                "    <!-- CSS only -->\r\n" +
                "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-uWxY/CJNBR+1zjPWmfnSnVxwRheevXITnMqoEIeG1LJrdI0GlVs/9cVSyPYXdcSF\" crossorigin=\"anonymous\">\r\n" +
                "</head>\r\n" +
                "<body>\r\n" +
                "    <div class=\"container\">\r\n" +
                "        <div class=\"jumbotron\">\r\n" +
                "            <h1 class=\"display-6\"><strong>Seja bem vindo(a), $USERNAME!</strong></h1>\r\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\r\n" +
                "            <hr class=\"my-4\">\r\n" +
                "            <p>Clique no botão abaixo para ativar sua conta e ter acesso ao sistema.</p>\r\n" +
                "            <p class=\"lead\">\r\n" +
                "              <a class=\"btn btn-primary btn-lg\" href=\"$BASEURL?token=$TOKEN\" role=\"button\">Ativar conta</a>\r\n" +
                "            </p>\r\n" +
                "        </div>\r\n" +
                "    </div>\r\n" +
                "</body>\r\n" +
                "</html>";
    }
}
