package com.example.demo.controller;

import com.example.demo.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SessionController {

    @Autowired
    private UserValidationService userValidationService;

//    @GetMapping("/test-session")
//    public Map<String, Object> testActiveSession() {
//        // Llama al método para verificar la sesión activa
//        userValidationService.checkActiveSession();
//
//        // Prepara la respuesta
//        Map<String, Object> response = new HashMap<>();
//        response.put("isActiveSession", userValidationService.isActiveSession());
//        response.put("activeUsername", userValidationService.getActiveUsername());
//
//        return response;
//    }
}
