package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.User.SignInDTO;
import com.library_management.librarymanagement.DTOs.User.SignUpDTO;
import com.library_management.librarymanagement.DTOs.User.UserUpdateDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManagementService {
    private UserRep userRep;

    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserServ userServ;

    public UserManagementService(UserRep userRep, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserServ userServ) {
        this.userRep = userRep;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userServ = userServ;
    }

    public SignUpDTO signup(SignUpDTO registrationRequest) {
        SignUpDTO resp = new SignUpDTO();
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setRole("USER");
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

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

    public SignInDTO signin(SignInDTO loginRequest) {
        SignInDTO resp = new SignInDTO();
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Successfully logged in");

            User user = userRep.findByUsername(loginRequest.getUsername()).orElseThrow();
            resp.setRole(user.getRole());
        } catch (Exception e) {
            resp.setStatusCode(500);
            resp.setMessage(e.getMessage());
        }
        return resp;
    }

    public List<User> getAllUsers() {
        List<User> usersList = userRep.findAll();
        return usersList;
    }

    public SignInDTO getUserByID(Long id) {
        SignInDTO signInDTO = new SignInDTO();
        try {
            User user = userRep.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
            signInDTO.setStatusCode(200);
            signInDTO.setUser(user);
            signInDTO.setMessage("User retrieved successfully");
        } catch (Exception e) {
            signInDTO.setStatusCode(500);
            signInDTO.setMessage(e.getMessage());
        }
        return signInDTO;
    }

    public Long deleteUser(Long id) {
        if (userRep.existsById(id)) {
            userRep.deleteById(id);
            return id;
        }
        throw new IllegalArgumentException("User not found");
    }

    public Long updateUser(Long id, UserUpdateDTO updatedUser) {
        try {
            User existingUser = userRep.findById(id).orElseThrow();
            if (updatedUser.getEmail()!=null && !updatedUser.getEmail().isBlank()){
                existingUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getUsername()!=null && !updatedUser.getUsername().isBlank()){
                existingUser.setUsername(updatedUser.getUsername());
            }
            if(updatedUser.getRole()!=null && !updatedUser.getRole().isBlank()){
                existingUser.setRole(updatedUser.getRole());
            }

            if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }

            userRep.save(existingUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public User getUserInfo(String username) {
        if (!username.isEmpty() && !username.isBlank()) {
            User user = userRep.findByUsername(username).orElseThrow();
            return user;
        }
        throw new IllegalArgumentException("User not found");
    }
}
