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

/**
 * Service class for managing user operations including authentication and user data management.
 * Handles user registration, authentication, and profile management functionalities.
 */
@Service
public class UserManagementService {
    private UserRep userRep;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserServ userServ;

    /**
     * Constructs a new UserManagementService with required dependencies.
     *
     * @param userRep Authentication repository for user data access
     * @param authenticationManager Spring Security authentication manager
     * @param passwordEncoder Password encoder for secure password storage
     * @param userServ User service for additional user operations
     */
    public UserManagementService(UserRep userRep, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserServ userServ) {
        this.userRep = userRep;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userServ = userServ;
    }

    /**
     * Registers a new user in the system.
     * Creates a new user with encoded password and default USER role.
     *
     * @param registrationRequest DTO containing registration information
     * @return SignUpDTO containing registration status and any error messages
     */
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

    /**
     * Authenticates a user and creates a security context.
     *
     * @param loginRequest DTO containing login credentials
     * @return SignInDTO containing authentication status and user role
     */
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

    /**
     * Retrieves all users from the system.
     *
     * @return List of all User entities
     */
    public List<User> getAllUsers() {
        List<User> usersList = userRep.findAll();
        return usersList;
    }

    /**
     * Retrieves user information by ID.
     *
     * @param id The ID of the user to retrieve
     * @return SignInDTO containing user information and status
     */
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

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete
     * @return The ID of the deleted user
     * @throws IllegalArgumentException if the user is not found
     */
    public Long deleteUser(Long id) {
        if (userRep.existsById(id)) {
            userRep.deleteById(id);
            return id;
        }
        throw new IllegalArgumentException("User not found");
    }

    /**
     * Updates user information.
     * Only updates fields that are provided and not blank in the DTO.
     *
     * @param id The ID of the user to update
     * @param updatedUser DTO containing the updated user information
     * @return The ID of the updated user
     */
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
            if(updatedUser.getCity()!=null && !updatedUser.getCity().isBlank()){
                existingUser.setCity(updatedUser.getCity());
            }
            if(updatedUser.getPhone()!=null && !updatedUser.getPhone().isBlank()){
                existingUser.setPhone(updatedUser.getPhone());
            }
            userRep.save(existingUser);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     * Retrieves a user by username.
     *
     * @param username The username to search for
     * @return The User entity
     * @throws IllegalArgumentException if the username is empty or user not found
     */
    public User getUserInfo(String username) {
        if (!username.isEmpty() && !username.isBlank()) {
            User user = userRep.findByUsername(username).orElseThrow(()->new RuntimeException("User not found"));
            return user;
        }
        throw new IllegalArgumentException("User not found");
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to retrieve
     * @return The User entity
     * @throws IllegalArgumentException if the ID is null or user not found
     */
    public User getUserInfoById(Long id) {
        if(id != null){
            return userRep.getReferenceById(id);
        }
        throw new IllegalArgumentException("User not found");
    }
}