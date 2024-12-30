package com.example.weatherapp.controller;

import com.example.weatherapp.model.User;
import com.example.weatherapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/activate")
    public User activateUser(@RequestParam String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setActive(true);
        return userRepository.save(user);
    }

    @PostMapping("/deactivate")
    public User deactivateUser(@RequestParam String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        user.setActive(false);
        return userRepository.save(user);
    }
}