package com.example.toeicwebsite.controller;

import com.example.toeicwebsite.data.dto.UserDTO;
import com.example.toeicwebsite.data.repository.UserRepository;
import com.example.toeicwebsite.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/updateNguoiDung")
    public ResponseEntity<?> updateNguoiDung(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.updateNguoiDung(userDTO));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping("/profile")
    public ResponseEntity<?> getNguoiDungHienTai() {
        return ResponseEntity.ok(userService.getNguoiDungHienTai());
    }
//    @PostMapping("/request")
//    public ResponseEntity<String> requestResetPassword(@RequestParam("email") String email) {
//        User user = userService.getUserByEmail(email);
//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email not found");
//        }
//
//        String token = UUID.randomUUID().toString();
//        userService.createPasswordResetTokenForUser(token);
//        userService.sendPasswordResetEmail(user, token);
//
//        return ResponseEntity.ok("Password reset email sent");
//    }
//
//    @PostMapping("/reset")
//    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestParam("password") String password) {
//        try {
//            userService.resetPassword(token, password);
//            return ResponseEntity.ok("Password has been reset");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
}
