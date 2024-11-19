package com.example.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class UserValidationService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper; // Para convertir JSON en Map

    private boolean isActiveSession = false;
    private String activeUsername = null;

    public UserValidationService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void checkActiveSession() {
        String url = "http://localhost:8082/api/user/login/active-session"; // URL para obtener la sesión activa
        try {
            // Realiza la consulta y recibe la respuesta como String
            String response = restTemplate.getForObject(url, String.class);

            // Convierte la respuesta JSON en un Map
            Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);

            // Asigna los valores a variables
            if (responseMap != null) {
                isActiveSession = (boolean) responseMap.getOrDefault("isLoggedIn", false);
                activeUsername = (String) responseMap.getOrDefault("username", null);
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
