package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {
    private final Map<String, User> users = new HashMap<>();

    public LoginService() {
        users.put("pepe", new User("pepe", "741"));
        users.put("luis", new User("luis", "852"));
        users.put("jose", new User("jose", "963"));
    }

    public String login(String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            user.setLoggedIn(true);
            return "Login successful for user: " + username;
        }
        return "Invalid username or password";
    }

    public String logout(String username) {
        User user = users.get(username);
        if (user != null && user.isLoggedIn()) {
            user.setLoggedIn(false);
            return "User " + username + " logged out successfully.";
        }
        return "User " + username + " is not logged in.";
    }

    public boolean isLoggedIn(String username) {
        User user = users.get(username);
        return user != null && user.isLoggedIn();
    }

    public String getActiveSession() {
        for (User user : users.values()) {
            if (user.isLoggedIn()) {
                return "Active session: " + user.getUsername();
            }
        }
        return "No active sessions.";
    }
}
