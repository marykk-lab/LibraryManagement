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

/**
 * AuthController is responsible for handling authentication and user management REST API requests.
 * It provides endpoints for user signup, signin, user profile retrieval, and administration
 * functionalities such as accessing, updating, and deleting user details.
 *
 * This controller communicates with the UserManagementService to perform the intended operations.
 */
@RestController
@RequestMapping("/auth/rest")
public class AuthController {

    private UserManagementService userManagementService;

    public AuthController(UserManagementService userManagementService) {
        this.userManagementService = userManagementService;
    }

    /**
     * Handles user signup by accepting user details and registering the user.
     * The method communicates with the UserManagementService to process the signup request.
     *
     * @param signUpDTO the DTO containing signup details such as username, password, and email
     * @return a ResponseEntity containing a success message if the user is successfully registered
     */
    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpDTO signUpDTO) {
        userManagementService.signup(signUpDTO);
        return ResponseEntity.ok("User registered.");
    }

    /**
     * Handles user signin by accepting user credentials and returning the authentication response.
     * The method communicates with the UserManagementService to process the signin request.
     *
     * @param signInDTO the DTO containing signin details such as username and password...
     * @return a ResponseEntity containing a SignInDTO object with authentication details, such as token, role, and status
     */
    @PostMapping("/signin")
    public ResponseEntity<SignInDTO> signIn(@RequestBody SignInDTO signInDTO) {
        SignInDTO signedInUser = userManagementService.signin(signInDTO);
        return ResponseEntity.ok(signedInUser);
    }

    /**
     * Retrieves a list of all registered users in the system.
     *
     * @return a ResponseEntity containing a list of User objects, representing all users
     */
    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userManagementService.getAllUsers());
    }

    /**
     * Retrieves the details of a user based on the provided user ID.
     * The method fetches the user details from the UserManagementService
     * and returns them in a SignInDTO structure.
     *
     * @param userId the unique identifier of the user to retrieve
     * @return a ResponseEntity containing a SignInDTO object, which includes user details and status information
     */
    @GetMapping("/admin/{userId}")
    public ResponseEntity<SignInDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userManagementService.getUserByID(userId));
    }

    /**
     * Retrieves the profile information of the currently authenticated user.
     * The method extracfts the username from the authenticaftion context and fetches
     * the corresponding user details using the UserManagementService.
     *
     * @return a ResponseEntity containing the User object associated with the authenticated user
     */
    @GetMapping("/get_profile")
    public ResponseEntity<User> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User response = userManagementService.getUserInfo(username);
        return ResponseEntity.ok(response);
    }

    /**
     * Updates the details of an existing user based on the provided user ID.
     * This method communicates with the UserManagementService to apply the updates.
     *
     * @param userId the unique identifier of the user to be updated
     * @param user the UserUpdateDTO containing the updated user details such as username, password, email, role, phone, and city
     * @return a ResponseEntity containing the ID of the updated user
     */
    @PutMapping("/admin/{userId}")
    public ResponseEntity<Long> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDTO user) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, user));
    }

    /**
     * Deletes a user with the specified user ID.
     * This method communicates with the UserManagementService to remove the user from the system.
     *
     * @param userId the unique identifier of the user to be deleted
     * @return a ResponseEntity containing a success message indicating the user has been deleted
     */
    @DeleteMapping("/admin/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userManagementService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }
}