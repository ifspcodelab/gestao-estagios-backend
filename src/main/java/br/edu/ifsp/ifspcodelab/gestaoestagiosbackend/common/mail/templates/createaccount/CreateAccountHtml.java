package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount;

public abstract class CreateAccountHtml {

    private CreateAccountHtml() {}

    public static String getMessageHtml() {
        return "<!DOCTYPE html>\n" +
               "<html lang=\"pt-br\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Roboto, \"Helvetica Neue\", sans-serif;\n" +
                "            color: #494949;\n" +
                "        }\n" +
                "\n" +
                "        .button-activate {\n" +
                "            padding-left: 2.5rem;\n" +
                "            padding-right: 2.5rem;\n" +
                "            background-color: #673ab7;\n" +
                "        }\n" +
                "\n" +
                "        .button-row a {\n" +
                "            text-align: center;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "            padding-left: 1.5rem;\n" +
                "            padding-right: 1.5rem;\n" +
                "            padding-top: 10px;\n" +
                "            padding-bottom: 10px;\n" +
                "            color: #fff;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: 500;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 4px;\n" +
                "        }\n" +
                "\n" +
                "        .lead {\n" +
                "            font-weight: 400;\n" +
                "        }\n" +
                "\n" +
                "        span {\n" +
                "            font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        .activate {\n" +
                "            margin-bottom: 30px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"jumbotron\">\n" +
                "            <h1 class=\"display-6\"><span>Seja bem vindo(a), $USERNAME!</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p class=\"activate\">Clique no botão abaixo para ativar sua conta e ter acesso ao sistema.</p>\n" +
                "            <div class=\"button-row\">\n" +
                "                <a class=\"button-activate\" href=\"$BASEURL/$IDUSER\" role=\"button\">Ativar conta</a>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getPasswordResetHtml() {
        return "<!DOCTYPE html>\n" +
            "<html lang=\"pt-br\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
            "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
            "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: Roboto, \"Helvetica Neue\", sans-serif;\n" +
            "            color: #494949;\n" +
            "        }\n" +
            "\n" +
            "        .button-activate {\n" +
            "            padding-left: 2.5rem;\n" +
            "            padding-right: 2.5rem;\n" +
            "            background-color: #673ab7;\n" +
            "        }\n" +
            "\n" +
            "        .button-row a {\n" +
            "            text-align: center;\n" +
            "            margin-left: auto;\n" +
            "            margin-right: auto;\n" +
            "            padding-left: 1.5rem;\n" +
            "            padding-right: 1.5rem;\n" +
            "            padding-top: 10px;\n" +
            "            padding-bottom: 10px;\n" +
            "            color: #fff;\n" +
            "            font-size: 14px;\n" +
            "            font-weight: 500;\n" +
            "            text-decoration: none;\n" +
            "            border-radius: 4px;\n" +
            "        }\n" +
            "\n" +
            "        .lead {\n" +
            "            font-weight: 400;\n" +
            "        }\n" +
            "\n" +
            "        span {\n" +
            "            font-weight: 700;\n" +
            "        }\n" +
            "\n" +
            "        .activate {\n" +
            "            margin-bottom: 30px;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"jumbotron\">\n" +
            "            <h1 class=\"display-6\"><span>Olá, $USERNAME!</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p class=\"activate\">Clique no botão abaixo para redefinir sua senha no sistema.</p>\n" +
            "            <div class=\"button-row\">\n" +
            "                <a class=\"button-activate\" href=\"$BASEURL/$IDUSER\" role=\"button\">Ativar conta</a>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getAdvisorRequestNotifyHtml() {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"pt-br\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n" +
                "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap\" rel=\"stylesheet\">\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Roboto, \"Helvetica Neue\", sans-serif;\n" +
                "            color: #494949;\n" +
                "        }\n" +
                "\n" +
                "        .button-activate {\n" +
                "            padding-left: 2.5rem;\n" +
                "            padding-right: 2.5rem;\n" +
                "            background-color: #673ab7;\n" +
                "        }\n" +
                "\n" +
                "        .button-row a {\n" +
                "            text-align: center;\n" +
                "            margin-left: auto;\n" +
                "            margin-right: auto;\n" +
                "            padding-left: 1.5rem;\n" +
                "            padding-right: 1.5rem;\n" +
                "            padding-top: 10px;\n" +
                "            padding-bottom: 10px;\n" +
                "            color: #fff;\n" +
                "            font-size: 14px;\n" +
                "            font-weight: 500;\n" +
                "            text-decoration: none;\n" +
                "            border-radius: 4px;\n" +
                "        }\n" +
                "\n" +
                "        .lead {\n" +
                "            font-weight: 400;\n" +
                "        }\n" +
                "\n" +
                "        span {\n" +
                "            font-weight: 700;\n" +
                "        }\n" +
                "\n" +
                "        .activate {\n" +
                "            margin-bottom: 30px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"jumbotron\">\n" +
                "            <h1 class=\"display-6\"><span>Olá, $USERNAME! Você recebeu um novo pedido de orientação.</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p class=\"activate\">Dados do pedido:</p>\n" +
                "           <ul>\n" +
                "                   <li><b>Aluno:</b> $STUDENTNAME</li>\n" +
                "                   <li><b>Matrícula:</b> $REGISTRATION</li>\n" +
                "                   <li><b>Tipo de estágio:</b> $INTERNSHIPTYPE</li>\n" +
                "                   <li><b>Prazo para avaliação:</b> $EXPIRESAT</li>\n" +
                "           </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
