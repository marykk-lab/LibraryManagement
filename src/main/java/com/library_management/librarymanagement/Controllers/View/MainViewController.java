package com.library_management.librarymanagement.Controllers.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainViewController {
    @GetMapping(path = "/")
    public String index(){
        return "index";
    }

    @GetMapping(path = "/profile")
    public String profile(Principal principal){
        if (principal==null){
            return null;
        }
        return "profile";
    }

    @GetMapping(path = "/register")
    public String register(){
        return "register";
    }

    @GetMapping(path = "/login")
    public String login(){
        return "login";
    }


}
