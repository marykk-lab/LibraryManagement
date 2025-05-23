package com.library_management.librarymanagement.Controllers.View;

import com.library_management.librarymanagement.DTOs.SignInDTO;
import com.library_management.librarymanagement.DTOs.SignUpDTO;
import com.library_management.librarymanagement.Repositories.UserRep;
import com.library_management.librarymanagement.Security.JWTCore;
import com.library_management.librarymanagement.Service.UserManagementService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/auth")
public class AuthViewController {

    private UserRep userRepository;
    private JWTCore jwtCore;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserManagementService userManagementService;

    public AuthViewController(UserRep userRepository, JWTCore jwtCore, UserManagementService userManagementService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtCore = jwtCore;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userManagementService = userManagementService;
    }


    @PostMapping("/signup")
    public String signUp(@ModelAttribute SignUpDTO signUpDTO, RedirectAttributes redirectAttributes) {
        if (userRepository.existsUserByUsername(signUpDTO.getUsername())) {
            redirectAttributes.addAttribute("error", "Username is already taken.");
            return "redirect:/signup";
        }
        userManagementService.signup(signUpDTO);
        return "redirect:/signin";
    }



    @PostMapping("/signin")
    public String signIn(@ModelAttribute SignInDTO signInDTO, RedirectAttributes redirectAttributes) {
        try {
            userManagementService.signin(signInDTO);
            return "redirect:/";
        } catch (BadCredentialsException e) {
            redirectAttributes.addAttribute("error", "Invalid username or password.");
            return "redirect:/signin";
        }
    }

}