package com.library_management.librarymanagement.Controllers.View;

import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Service.UserManagementService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
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

}