package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.JWTCore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRep userRepository;
    private final JWTCore jwtService;


    public AuthController(UserRep userRepository, JWTCore jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        User user = new User();
        user.setUsername(req.username());
        user.setEmail(req.email());
        user.setPassword(req.password());
        userRepository.save(user);
        return ResponseEntity.ok("User registered");
    }

    //@PostMapping("/login")
    //public ResponseEntity<String> login(@RequestBody LoginRequest req) {
    //    Authentication auth = authManager.authenticate(
    //            new UsernamePasswordAuthenticationToken(req.username(), req.password()));
//
    //    String jwt = jwtService.generateToken(auth);
    //    return ResponseEntity.ok(jwt);
    //}

    public record RegisterRequest(String username, String email, String password) {}
    public record LoginRequest(String username, String password) {}
}