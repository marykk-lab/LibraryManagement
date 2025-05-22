package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {
    @Autowired
    private UserRep userRep;
    @Autowired
    private SecurityConfig securityConfig;


    public SignUpDTO signup(SignUpDTO registrationRequest) {
        SignUpDTO resp = new SignUpDTO();
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setRole("USER");
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(securityConfig.passwordEncoder().encode(registrationRequest.getPassword()));

            User savedUser = userRep.save(user);
            if (savedUser.getUserID() > 0 && savedUser!=null) {
                resp.setMessage("User saved successfully");
                resp.setStatusCode(HttpStatus.CONFLICT.value());
            }
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return resp;
    }

    public SignInDTO login(SignInDTO loginRequest) {
        SignInDTO resp = new SignInDTO();
        try {
            //securityConfig.authenticationManager().authenticate(
            //        new UsernamePasswordAuthenticationToken(
            //                loginRequest.getUsername(),
            //                loginRequest.getPassword()
            //        )
            //);

            User user = userRep.findByUsername(loginRequest.getUsername()).orElseThrow();
            //String jwt = jwtUtils.generateToken(user);
            //String refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            resp.setStatusCode(200);
            //resp.setToken(jwt);
            //resp.setRefreshToken(refreshToken);
            //resp.setRole(user.getRole());
            //resp.setExpirationTime("24Hrs");
            //resp.setMessage("Successfully logged in");
        } catch (Exception e) {
            resp.setStatusCode(500);
            //resp.setMessage(e.getMessage());
        }
        return resp;
    }
}
