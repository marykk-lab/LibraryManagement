package com.library_management.librarymanagement.Controllers.View;

import com.library_management.librarymanagement.DTOs.User.SignInDTO;
import com.library_management.librarymanagement.DTOs.User.SignUpDTO;
import com.library_management.librarymanagement.DTOs.User.UserUpdateDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.UserDetailsImpl;
import com.library_management.librarymanagement.Service.UserManagementService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for handling user authentication, registration, profile, and admin user management operations.
 */
@Controller
@RequestMapping("/auth")
public class UserViewController {

    private final UserRep userRepository;
    private final UserManagementService userManagementService;

    /**
     * Constructs a new UserViewController with the given user repository and user management service.
     *
     * @param userRepository the repository for user data access
     * @param userManagementService the service for managing user-related operations
     */
    public UserViewController(UserRep userRepository, UserManagementService userManagementService) {
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    /**
     * Handles user registration requests.
     *
     * @param signUpDTO the registration data transfer object
     * @param redirectAttributes attributes for a redirect scenario
     * @return a redirect to the signup page if username exists, otherwise redirects to signin page
     */
    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO, RedirectAttributes redirectAttributes) {
        if (userRepository.existsUserByUsername(signUpDTO.getUsername())) {
            redirectAttributes.addAttribute("error", "Username is already taken.");
            return "redirect:/auth/signup";
        }
        userManagementService.signup(signUpDTO);
        redirectAttributes.addFlashAttribute("success", "Registration successful. Please sign in.");
        return "redirect:/auth/signin";
    }

    /**
     * Allows admin to update a user's information.
     *
     * @param userUpdateDTO the user update data transfer object
     * @param userId the ID of the user to update
     * @param redirectAttributes attributes for a redirect scenario
     * @return a redirect to the admin dashboard
     */
    @PostMapping("/admin/update/{userId}")
    public String updateUserAdmin(@ModelAttribute UserUpdateDTO userUpdateDTO, @PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userManagementService.updateUser(userId, userUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "User updated successfully.");
        return "redirect:/admin/dashboard";
    }

    /**
     * Allows admin to delete a user.
     *
     * @param userId the ID of the user to delete
     * @param redirectAttributes attributes for a redirect scenario
     * @return a redirect to the admin dashboard
     */
    @PostMapping("/admin/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        try{
            userManagementService.deleteUser(userId);
            redirectAttributes.addFlashAttribute("message", "User deleted successfully.");

        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Failed to delete user: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Allows a user to update their profile information.
     *
     * @param userUpdateDTO the user update data transfer object
     * @param userId the ID of the user to update
     * @param redirectAttributes attributes for a redirect scenario
     * @return a redirect to the user profile page
     */
    @PostMapping("/profile/update/{userId}")
    public String updateProfile(@ModelAttribute UserUpdateDTO userUpdateDTO, @PathVariable Long userId, RedirectAttributes redirectAttributes){
        try{
            userManagementService.updateUser(userId, userUpdateDTO);
            redirectAttributes.addFlashAttribute("message", "User updated successfully.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Failed to update user: " + e.getMessage());
        }
        return "redirect:/auth/profile";
    }

    /**
     * Displays the signup page.
     *
     * @param signUpDTO the registration data transfer object
     * @param error error message to be displayed, if any
     * @param model the model to add attributes to
     * @return the register view
     */
    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO,
                             @RequestParam(value = "error", required = false) String error,
                             Model model) {
        model.addAttribute("error", error);
        return "register";
    }

    /**
     * Displays the signin page.
     *
     * @param model the model to add attributes to
     * @return the login view
     */
    @GetMapping("/signin")
    public String signinPage(Model model) {
        model.addAttribute("signInDTO", new SignInDTO());
        return "login";
    }

    /**
     * Displays the profile page of the currently authenticated user.
     *
     * @param model the model to add attributes to
     * @return the profile view
     */
    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User response = userManagementService.getUserInfoById(userDetails.getId());
        model.addAttribute("user", response);
        return "profile";
    }

    /**
     * Displays the admin view with a list of all users.
     *
     * @param model the model to add attributes to
     * @return the user list admin view
     */
    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user_list_admin";
    }

    /**
     * Displays the user profile update form for the currently authenticated user.
     *
     * @param model the model to add attributes to
     * @return the update user view
     */
    @GetMapping("/profile/update")
    public String updateProfileForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userManagementService.getUserInfoById(userDetails.getId());

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        userUpdateDTO.setUserID(user.getUserID());
        userUpdateDTO.setUsername(user.getUsername());
        userUpdateDTO.setEmail(user.getEmail());
        userUpdateDTO.setCity(user.getCity());
        userUpdateDTO.setPhone(user.getPhone());
        model.addAttribute("user", userUpdateDTO);
        return "update_user";
    }
}