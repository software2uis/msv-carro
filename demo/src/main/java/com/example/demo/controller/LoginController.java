package com.example.demo.controller;

import com.example.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public String login(@RequestParam String username, @RequestParam String password) {
        return loginService.login(username, password);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String username) {
        return loginService.logout(username);
    }

    @GetMapping("/status")
    public String checkLoginStatus(@RequestParam String username) {
        boolean isLoggedIn = loginService.isLoggedIn(username);
        return isLoggedIn ? "User " + username + " is logged in." : "User " + username + " is not logged in.";
    }

    @GetMapping("/active-session")
    public String getActiveSession() {
        return loginService.getActiveSession();
    }
}
