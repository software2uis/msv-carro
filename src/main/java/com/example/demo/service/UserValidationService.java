package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserValidationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Para convertir JSON en Map

    @Value("${usuario.service.url}")
    String url;

    private boolean isActiveSession = false;
    private String activeUsername = null;

    public UserValidationService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void checkActiveSession(String  username) {
        String end = url + "/api/user/login/active-session/"+username; // URL para obtener la sesión activa
        try {
            // Realiza la consulta y recibe la respuesta como String
            boolean response = restTemplate.getForObject(end, boolean.class);



            // Asigna los valores a variables
            if (response) {
                isActiveSession = response;
                activeUsername = username;
            } else {
                resetSessionData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            resetSessionData(); // En caso de error, resetea los datos de sesión
        }
    }

    private void resetSessionData() {
        isActiveSession = false;
        activeUsername = null;
    }

    // Método para obtener el estado de la sesión activa
    public boolean isActiveSession() {
        return isActiveSession;
    }

    // Método para obtener el nombre del usuario de la sesión activa
    public String getActiveUsername() {
        return activeUsername;
    }
}
