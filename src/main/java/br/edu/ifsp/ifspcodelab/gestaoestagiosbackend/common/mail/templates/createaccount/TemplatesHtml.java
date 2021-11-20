package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.templates.createaccount;

public abstract class TemplatesHtml {

    private TemplatesHtml() {}

    public static String getCreateAccount() {
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

    public static String getPasswordReset() {
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

    public static String getAdvisorRequestNotify() {
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
                "                   <li><b>Data limite para avaliar:</b> $EXPIRESAT</li>\n" +
                "           </ul>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getStudentNotificationExpired() {
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
                "            <h1 class=\"display-6\"><span>Olá, $USERNAME! Seu pedido de orientação expirou e não foi avaliado.</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p class=\"activate\">Dados do pedido:</p>\n" +
                "           <ul>\n" +
                "                   <li><b>Orientador:</b> $ADVISORNAME</li>\n" +
                "                   <li><b>Data da solicitação:</b> $REQUESTCREATEDATE</li>\n" +
                "           </ul>\n" +
                "           <br/>" +
                "            <div class=\"button-row\">\n" +
                "                <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
                "            </div>\n" +
                "           <br/>" +
                "            <p>Você precisa criar um novo pedido, recomendamos direcionar a outro orientador.</p>" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getRequestAppraisal() {
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
            "            <h1 class=\"display-6\"><span>$ISDEFERRED</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p class=\"activate\">Dados do pedido:</p>\n" +
            "           <ul>\n" +
            "                   <li><b>Aluno:</b> $STUDENTNAME</li>\n" +
            "                   <li><b>Orientador:</b> $ADVISORNAME</li>\n" +
            "                   <li><b>Detalhes:</b> $DETAILS</li>\n" +
            "           </ul>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getPlanApproved() {
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
            "            <h1 class=\"display-6\"><span>Plano de atividades deferido!</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p>Prezado(a) $STUDENTNAME, </p>" +
            "            <p>Informamos que o orientador $ADVISORNAME avaliou seu Plano de Atividades e marcou como deferido.</p>" +
            "            <br/><br/>" +
            "            <p class=\"activate\">Avaliação: </p>" +
            "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getPlanIndeferred() {
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
                "            <h1 class=\"display-6\"><span>Plano de atividades indeferido.</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p>Prezado(a) $STUDENTNAME, </p>" +
                "            <p>Informamos que o orientador $ADVISORNAME avaliou e indeferiu seu plano de atividades.</p>" +
                "            <p class=\"activate\">Detalhes da avaliação: </p>" +
                "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
                "            <br/><br/><br/>" +
                "                <div class=\"button-row\">\n" +
                "                    <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
                "                </div>\n" +
                "            <br/>" +
                "            <p>Para prosseguir com o estágio você precisa reenviar o Plano de Atividades com as correções solicitadas pelo orientador.</p>" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getReportSentToStudent() {
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
            "            <h1 class=\"display-6\"><span>Você enviou seu $REPORT</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p class=\"activate\">Seu $REPORT referente ao mês de $MONTH foi enviado com sucesso.</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getReportSentToAdvisor() {
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
            "            <h1 class=\"display-6\"><span>Envio de $REPORT</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p class=\"activate\">O aluno $STUDENT enviou um $REPORT referente ao mês de $MONTH.</p>\n" +
            "            <div class=\"button-row\">\n" +
            "               <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
            "               </div>\n" +
            "            <br/>" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getReportApproved() {
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
            "            <h1 class=\"display-6\"><span>Deferimento do $REPORT</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p>Prezado(a) $STUDENT, </p>" +
            "            <p>Informamos que o orientador $ADVISOR avaliou e aprovou seu $REPORT referente ao mês de $MONTH.</p>" +
            "            <p>Detalhes: </p>" +
            "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getReportIndeferred() {
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
            "            <h1 class=\"display-6\"><span>Indeferimento do $REPORT</span></h1>\n" +
            "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p>Prezado(a) $STUDENT, </p>" +
            "            <p>Informamos que o orientador $ADVISOR avaliou e indeferiu seu $REPORT referente ao mês de $MONTH.</p>" +
            "            <p>Detalhes: </p>" +
            "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
            "            <hr class=\"my-4\">\n" +
            "            <p>Por favor, reenvie o $REPORT com as devidas correções sugeridas pelo orientador.</p>" +
            "            <div class=\"button-row\">\n" +
            "               <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
            "            </div>\n" +
            "            <br/>" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
    }

    public static String getRealizationTermSubmissionStudent() {
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
                "            <h1 class=\"display-6\"><span>Termo de Realização de Estágio enviado!</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p>Prezado(a) $STUDENTNAME, </p>" +
                "            <p>Seu <b/>Termo de Realização de Estágio</b> foi submetido com sucesso. Agora aguarde a avaliação do Termo pelo seu orientador.</p>" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getRealizationTermSubmissionAdvisor() {
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
                "            <h1 class=\"display-6\"><span>Novo Termo de Realização de Estágio recebido!</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p>Prezado(a) $ADVISORNAME, </p>" +
                "            <p>O aluno $STUDENTNAME enviou o <b/>Termo de Realização de Estágio</b> e aguarda sua avaliação.</p>" +
                "            <br/><br/><br/>" +
                "                <div class=\"button-row\">\n" +
                "                    <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
                "                </div>\n" +
                "            <br/>" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getRealizationTermApproved() {
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
                "            <h1 class=\"display-6\"><span>Termo de Realização de Estágio deferido!</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p>Prezado(a) $STUDENTNAME, </p>" +
                "            <p>Informamos que o orientador $ADVISORNAME avaliou seu <b>Termo de Realização de Estágio</b> e marcou como deferido.</p>" +
                "            <br/>" +
                "            <p class=\"activate\">Avaliação: </p>" +
                "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static String getRealizationTermIndeferred() {
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
                "            <h1 class=\"display-6\"><span>Termo de Realização de Estágio indeferido.</span></h1>\n" +
                "            <p class=\"lead\">Sistema de Apoio à Orientação de Estágios do Instituto Federal de São Paulo.</p>\n" +
                "            <hr class=\"my-4\">\n" +
                "            <p>Prezado(a) $STUDENTNAME, </p>" +
                "            <p>Informamos que o orientador $ADVISORNAME avaliou e indeferiu seu <b>Termo de Realização de Estágio</b>.</p>" +
                "            <p class=\"activate\">Detalhes da avaliação: </p>" +
                "            <span style=\"white-space: pre-line\">$DETAILS</span>\n" +
                "            <br/> \n" +
                "            <p>É necessário reenviar o Termo com as correções solicitadas pelo orientador.</p>" +
                "            <br/><br/><br/>" +
                "                <div class=\"button-row\">\n" +
                "                    <a class=\"button-activate\" href=\"$SYSTEMLINK\" role=\"button\">Acessar o sistema</a>\n" +
                "                </div>\n" +
                "            <br/>" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }
}
