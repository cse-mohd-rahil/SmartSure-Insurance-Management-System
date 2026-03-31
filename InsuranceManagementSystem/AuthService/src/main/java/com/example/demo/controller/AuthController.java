package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired   
    private UserRepository userRepository;

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();   
    }

    @GetMapping("/test")
    public String test() {
        return "JWT Working";
    }
}

//----------------------------------LOGGING--------------------------------------

// package com.example.demo.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.example.demo.dto.AuthResponse;
// import com.example.demo.dto.LoginRequest;
// import com.example.demo.dto.RegisterRequest;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import com.example.demo.service.AuthService;

// import jakarta.validation.Valid;

// // ✅ Logging
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @RestController
// @RequestMapping("/api")
// public class AuthController {

//     private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

//     @Autowired
//     private AuthService authService;

//     @Autowired
//     private UserRepository userRepository;

//     @PostMapping("/auth/register")
//     public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {

//         logger.info("Register API called for email: {}", request.getEmail());

//         try {
//             authService.register(request);
//             logger.info("User registered successfully: {}", request.getEmail());
//             return ResponseEntity.ok("User registered successfully");

//         } catch (Exception e) {
//             logger.error("Error during registration: {}", request.getEmail(), e);
//             throw e;
//         }
//     }

//     @PostMapping("/auth/login")
//     public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {

//         logger.info("Login API called for email: {}", request.getEmail());

//         try {
//             AuthResponse response = authService.login(request);
//             logger.info("User logged in successfully: {}", request.getEmail());
//             return ResponseEntity.ok(response);

//         } catch (Exception e) {
//             logger.error("Login failed for email: {}", request.getEmail(), e);
//             throw e;
//         }
//     }

//     @GetMapping("/users")
//     public List<User> getAllUsers() {

//         logger.info("Fetching all users");

//         List<User> users = userRepository.findAll();

//         logger.info("Total users fetched: {}", users.size());

//         return users;
//     }

//     @GetMapping("/test")
//     public String test() {
//         logger.debug("Test API called");
//         return "JWT Working";
//     }
// }