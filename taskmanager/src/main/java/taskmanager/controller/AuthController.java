package taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskmanager.User;
import taskmanager.service.UserService;

@RestController
public class AuthController {

    @Autowired
    UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return service.login(user); // ✅ ONLY THIS
    }
}