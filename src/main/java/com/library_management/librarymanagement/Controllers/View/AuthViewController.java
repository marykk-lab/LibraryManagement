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

@Controller
@RequestMapping("/auth")
public class AuthViewController {

    private final UserRep userRepository;
    private final UserManagementService userManagementService;

    public AuthViewController(UserRep userRepository, UserManagementService userManagementService) {
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

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

    @PostMapping("/admin/update/{userId}")
    public String updateUserAdmin(@ModelAttribute UserUpdateDTO userUpdateDTO, @PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userManagementService.updateUser(userId, userUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "User updated successfully.");
        return "redirect:/admin/dashboard";
    }

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
    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO,
                             @RequestParam(value = "error", required = false) String error,
                             Model model) {
        model.addAttribute("error", error);
        return "register";
    }

    @GetMapping("/signin")
    public String signinPage(Model model) {
        model.addAttribute("signInDTO", new SignInDTO());
        return "login";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User response = userManagementService.getUserInfoById(userDetails.getId());
        model.addAttribute("user", response);
        return "profile";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user_list_admin";
    }

    @GetMapping("/profile/update")
    public String updateProfileForm(Model model){
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