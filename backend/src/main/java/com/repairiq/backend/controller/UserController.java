package com.repairiq.backend.controller;

import com.repairiq.backend.model.User;
import com.repairiq.backend.repository.UserRepository;
import com.repairiq.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    private final Map<String, String> otpStorage = new ConcurrentHashMap<>();
    private final Map<String, Long> otpExpiry = new ConcurrentHashMap<>();

    private final SecureRandom random = new SecureRandom();

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userRepository.save(user);
        return "Registration successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser == null) {
            return "User not found";
        }

        if (!existingUser.getPassword().equals(user.getPassword())) {
            return "Invalid password";
        }

        String otp = String.format("%06d", random.nextInt(1000000));

        otpStorage.put(user.getEmail(), otp);
        otpExpiry.put(user.getEmail(), System.currentTimeMillis() + 5 * 60 * 1000);

        try {
            emailService.sendOtp(user.getEmail(), otp);
            return "OTP sent";
        } catch (Exception e) {
            otpStorage.remove(user.getEmail());
            otpExpiry.remove(user.getEmail());
            return "Unable to send OTP";
        }
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestBody Map<String, String> request) {

        String email = request.get("email");
        String enteredOtp = request.get("otp");

        if (email == null || enteredOtp == null) {
            return "Email and OTP are required";
        }

        String storedOtp = otpStorage.get(email);
        Long expiry = otpExpiry.get(email);

        if (storedOtp == null || expiry == null) {
            return "OTP not found or expired";
        }

        if (System.currentTimeMillis() > expiry) {
            otpStorage.remove(email);
            otpExpiry.remove(email);
            return "OTP expired";
        }

        if (!storedOtp.equals(enteredOtp)) {
            return "Invalid OTP";
        }

        otpStorage.remove(email);
        otpExpiry.remove(email);

        return "OTP verified";
    }

    @GetMapping
    public java.util.List<User> getUsers() {
        return userRepository.findAll();
    }
}