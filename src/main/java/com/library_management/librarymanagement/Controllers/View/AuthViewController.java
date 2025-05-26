package com.library_management.librarymanagement.Controllers.View;

import com.library_management.librarymanagement.DTOs.AuthorUpdateDTO;
import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.DTOs.UserUpdateDTO;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Service.UserManagementService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/auth")
public class AuthViewController {

    private final UserRep userRepository;
    private final UserManagementService userManagementService;

    public AuthViewController(UserRep userRepository, UserManagementService userManagementService) {
        this.userRepository = userRepository;
        this.userManagementService = userManagementService;
    }

    @GetMapping("/signup")
    public String signupPage(@ModelAttribute("signUpDTO") SignUpDTO signUpDTO,
                             @RequestParam(value = "error", required = false) String error,
                             Model model) {
        model.addAttribute("error", error);
        return "register";
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

    @GetMapping("/signin")
    public String signinPage(Model model) {
        model.addAttribute("signInDTO", new SignInDTO());
        return "login";
    }

    @GetMapping("/admin")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "user_list_admin";
    }

    @PostMapping("/admin/update/{userId}")
    public String updateUser(@ModelAttribute UserUpdateDTO userUpdateDTO, @PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userManagementService.updateUser(userId, userUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "User updated successfully.");
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        userManagementService.deleteUser(userId);
        redirectAttributes.addFlashAttribute("message", "User deleted successfully.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User response = userManagementService.getUserInfo(username);
        model.addAttribute("user", response);
        return "profile";
    }

}