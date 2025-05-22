package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.JWTCore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/rest")
public class AuthController {

    private UserRep userRepository;
    private JWTCore jwtCore;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    public AuthController(UserRep userRepository, JWTCore jwtCore, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtCore = jwtCore;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        if (userRepository.existsUserByUsername(signUpDTO.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken.");
        }
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered.");
    }


    //@PostMapping("/signin")
    //public ResponseEntity<String> signIn(@RequestBody SignInDTO signInDTO) {
    //    Authentication authentication = null;
    //    try{
    //        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getUsername(), signInDTO.getPassword()));
    //    }catch (BadCredentialsException e){
    //        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    //    }
    //    SecurityContextHolder.getContext().setAuthentication(authentication);
    //    String jwt = jwtCore.generateToken(authentication);
    //    return ResponseEntity.ok(jwt);
    //}
}