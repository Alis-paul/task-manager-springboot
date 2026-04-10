package taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import taskmanager.User;
import taskmanager.repository.UserRepository;
import taskmanager.security.JwtUtil;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    @Autowired
    PasswordEncoder encoder;

    public User register(User user) {
        // FIX: was calling setRole("USER") then immediately setRole("ADMIN"), overwriting first call
        // Default role is USER; only promote to ADMIN explicitly if needed
        if (user.getRole() == null || user.getRole().isBlank()) {
            user.setRole("USER");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String login(User user) {
        User existing = repo.findByUsername(user.getUsername());

        if (existing != null && encoder.matches(user.getPassword(), existing.getPassword())) {
            // FIX: generateToken now accepts role as second parameter
            return JwtUtil.generateToken(existing.getUsername(), existing.getRole());
        }

        return "invalid credentials";
    }
}