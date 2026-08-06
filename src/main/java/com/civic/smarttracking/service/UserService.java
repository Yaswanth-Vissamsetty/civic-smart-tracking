package com.civic.smarttracking.service;

import com.civic.smarttracking.entity.User;
import com.civic.smarttracking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already registered. Please login or use a different email.");
        }
        user.setRole("CITIZEN");
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password) && "CITIZEN".equals(user.getRole())) {
                return user;
            }
        }
        throw new RuntimeException("Invalid email or password!");
    }

    public User loginAdmin(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password) && "ADMIN".equals(user.getRole())) {
                return user;
            }
        }
        // Fallback check for default admin credentials if not in database yet
        if ("admin@civic.gov.in".equals(email) && "admin123".equals(password)) {
            User admin = new User("Municipal Admin", "admin@civic.gov.in", "admin123", "9876543210", "ADMIN");
            return userRepository.save(admin);
        }
        throw new RuntimeException("Invalid Admin Credentials!");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with Email: " + email));
    }

    public User updateProfile(Long id, String name, String mobile, String password) {
        User user = getUserById(id);
        if (name != null && !name.trim().isEmpty()) {
            user.setName(name);
        }
        if (mobile != null && !mobile.trim().isEmpty()) {
            user.setMobile(mobile);
        }
        if (password != null && !password.trim().isEmpty()) {
            user.setPassword(password);
        }
        return userRepository.save(user);
    }

    public List<User> getAllCitizens() {
        return userRepository.findByRole("CITIZEN");
    }

    public long getTotalCitizensCount() {
        return userRepository.findByRole("CITIZEN").size();
    }
}
