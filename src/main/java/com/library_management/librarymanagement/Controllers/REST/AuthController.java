package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.JWTCore;
import com.library_management.librarymanagement.Service.UserManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/rest")
public class AuthController {

    private UserRep userRepository;
    private JWTCore jwtCore;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserManagementService userManagementService;

    public AuthController(UserRep userRepository, JWTCore jwtCore, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserManagementService userManagementService) {
        this.userRepository = userRepository;
        this.jwtCore = jwtCore;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userManagementService = userManagementService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        //if (userRepository.existsUserByUsername(signUpDTO.getUsername())){
        //    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
        //}
        //User user = new User();
        //user.setUsername(signUpDTO.getUsername());
        //user.setEmail(signUpDTO.getEmail());
        //user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        //userRepository.save(user);
        //return ResponseEntity.ok("User registered.");
        userManagementService.signup(signUpDTO);
        return ResponseEntity.ok("User registered.");
    }


    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
        userManagementService.signin(signInDTO);
        return ResponseEntity.ok("User logged in.");
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    @GetMapping("/admin/{userId}")
    public ResponseEntity<SignInDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userManagementService.getUserByID(userId));
    }

    @PutMapping("/admin/{userId}")
    public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, user));
    }

    @GetMapping("/get_profile")
    public ResponseEntity<User> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User response = userManagementService.getUserInfo(username);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }
}