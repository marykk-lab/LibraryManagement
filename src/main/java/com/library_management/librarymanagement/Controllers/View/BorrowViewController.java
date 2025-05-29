package com.library_management.librarymanagement.Controllers.View;

import java.util.List;
import java.util.Set;

import com.library_management.librarymanagement.DTOs.Borrow.BorrowUpdateDTO;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Security.UserDetailsImpl;
import com.library_management.librarymanagement.Service.BookServ;
import com.library_management.librarymanagement.Service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library_management.librarymanagement.DTOs.Borrow.BorrowDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.Service.BorrowServ;

/**
 * Controller for handling borrow-related view operations in the library management system.
 */
@Controller
@RequestMapping("borrow")
public class BorrowViewController {
    @Autowired
    private BorrowServ borrowServ;

    @Autowired
    private BookServ bookServ;

    @Autowired
    private UserManagementService userManagementService;

    /**
     * Handles admin's request to add a new borrow record.
     * 
     * @param borrowSaveDTO DTO containing borrow details
     * @param redirectAttributes for flash messages
     * @return redirect to admin dashboard
     */
    @PostMapping(path = "/admin/add")
    public String addBorrowAdmin(@ModelAttribute BorrowSaveDTO borrowSaveDTO, RedirectAttributes redirectAttributes){
        try{
            borrowServ.addBorrow(borrowSaveDTO);
            redirectAttributes.addFlashAttribute("message", "Borrow was succesfully created.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Handles user's request to borrow a book.
     * 
     * @param borrowSaveDTO DTO containing borrow details
     * @param redirectAttributes for flash messages
     * @return redirect to home page
     */
    @PostMapping("/add")
    public String addBorrowUser(@ModelAttribute BorrowSaveDTO borrowSaveDTO, RedirectAttributes redirectAttributes) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            borrowSaveDTO.setUserID(userDetails.getId());
            System.out.println(borrowSaveDTO.getUserID());
            System.out.println(borrowSaveDTO);
            borrowServ.addBorrow(borrowSaveDTO);
            redirectAttributes.addFlashAttribute("message", "You have successfully borrowed the book.");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/";
    }

    /**
     * Removes a borrow record by its ID.
     * 
     * @param id the ID of the borrow record to delete
     * @param redirectAttributes for flash messages
     * @return redirect to admin dashboard
     */
    @PostMapping(path = "/admin/delete/{id}")
    public String removeBorrow(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        borrowServ.deleteBorrowByID(id);
        redirectAttributes.addFlashAttribute("message", "Borrow deleted succesfully");
        return "redirect:/admin/dashboard";
    }

    /**
     * Updates an existing borrow record.
     * 
     * @param borrowUpdateDTO DTO containing updated borrow details
     * @param id the ID of the borrow record to update
     * @param redirectAttributes for flash messages
     * @return redirect to admin dashboard
     */
    @PostMapping(path = "/admin/update/{id}")
    public String updateBorrow(@ModelAttribute BorrowUpdateDTO borrowUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        borrowServ.updateBorrow(borrowUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Borrow was succesfully updated.");
        return "redirect:/admin/dashboard";
    }

    /**
     * Displays the form for adding a new borrow record (admin view).
     * 
     * @param model Spring MVC model
     * @return add borrow form view
     */
    @GetMapping(path = "/admin/add")
    public String addBorrowFormAdmin(Model model) {
        model.addAttribute("borrow", new BorrowSaveDTO());
        model.addAttribute("users", userManagementService.getAllUsers());
        model.addAttribute("books", bookServ.getBooks());
        return "add_borrow";
    }

    /**
     * Displays list of all borrows (admin view).
     * 
     * @param model Spring MVC model
     * @return admin borrows list view
     */
    @GetMapping(path = "/admin")
    public String getBorrowsAdmin(Model model){
        List<BorrowDTO> borrows =  borrowServ.getBorrows();
        model.addAttribute("borrows", borrows);
        return "borrows_list_admin";
    }

    /**
     * Displays the form for updating a borrow record.
     * 
     * @param id ID of the borrow record to update
     * @param model Spring MVC model
     * @return update borrow form view
     */
    @GetMapping(path = "/admin/update/{id}")
    public String updateBorrowForm(@PathVariable Long id, Model model) {
        BorrowDTO borrowDTO = borrowServ.getBorrowByID(id);
        model.addAttribute("borrow", borrowDTO);
        model.addAttribute("users", userManagementService.getAllUsers());
        model.addAttribute("books", bookServ.getBooks());
        return "update_borrow";
    }

    /**
     * Displays list of current user's borrows.
     * 
     * @param model Model for giving template neccessary data.
     * @return user borrows list view
     */
    @GetMapping()
    public String getBorrows(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userManagementService.getUserInfoById(userDetails.getId());
        Set<Borrow> borrows = user.getBorrowSet();
        model.addAttribute("borrows", borrows);
        return "borrows_list";
    }
}
