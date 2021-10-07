package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config;

import java.util.HashMap;
import java.util.Map;

public abstract class CreatorParametersMail {

    private CreatorParametersMail() {}

    public static Map<String, String> activateAccount(String username, String baseUrl, String token) {
        Map<String, String> map = new HashMap<>();

        map.put("$USERNAME", username);
        map.put("$BASE_URL", baseUrl);
        map.put("$TOKEN", token);

        return map;
    }

}
