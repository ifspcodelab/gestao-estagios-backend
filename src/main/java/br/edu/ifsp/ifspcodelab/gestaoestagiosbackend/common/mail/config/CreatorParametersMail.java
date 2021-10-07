package br.edu.ifsp.ifspcodelab.gestaoestagiosbackend.common.mail.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class CreatorParametersMail {

    private CreatorParametersMail() {}

    public static Map<String, String> activateAccount(String username, String baseUrl, UUID userId) {
        Map<String, String> map = new HashMap<>();

        map.put("$USERNAME", username);
        map.put("$BASEURL", baseUrl);
        map.put("$IDUSER", userId.toString());

        return map;
    }

}
