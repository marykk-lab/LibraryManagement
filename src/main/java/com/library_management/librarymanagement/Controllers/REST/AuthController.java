package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.User.SignInDTO;
import com.library_management.librarymanagement.DTOs.User.SignUpDTO;
import com.library_management.librarymanagement.DTOs.User.UserUpdateDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Service.UserManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/rest")
public class AuthController {

    private UserManagementService userManagementService;

    public AuthController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        userManagementService.signup(signUpDTO);
        return ResponseEntity.ok("User registered.");
    }


    @PostMapping("/signin")
    public ResponseEntity<SignInDTO> signIn(@RequestBody SignInDTO signInDTO) {
        SignInDTO signedInUser = userManagementService.signin(signInDTO);
        return ResponseEntity.ok(signedInUser);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<SignInDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userManagementService.getUserByID(userId));
    }
    @GetMapping("/get_profile")
    public ResponseEntity<User> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User response = userManagementService.getUserInfo(username);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/admin/{userId}")
    public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDTO user) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, user));
    }

    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }
}