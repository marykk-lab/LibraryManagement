package com.library_management.librarymanagement.Controllers.View;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling main view navigation in the library management system.
 */
@Controller
public class MainViewController {

    /**
     * Handles the request for the main index page.
     *
     * @return the name of the index view template
     */
    @GetMapping(path = "/")
    public String index(){
        return "index";
    }

    /**
     * Handles the request for the admin dashboard page.
     *
     * @return the name of the admin dashboard view template
     */
    @GetMapping(path = "/admin/dashboard")
    public String adminDashboard(){
        return "admin_dashboard";
    }

}