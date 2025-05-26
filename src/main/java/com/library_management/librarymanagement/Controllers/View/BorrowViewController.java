package com.library_management.librarymanagement.Controllers.View;

import java.util.List;

import com.library_management.librarymanagement.DTOs.BorrowUpdateDTO;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Service.BookServ;
import com.library_management.librarymanagement.Service.UserManagementService;
import com.library_management.librarymanagement.Service.UserServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library_management.librarymanagement.DTOs.BorrowDTO;
import com.library_management.librarymanagement.DTOs.BorrowSaveDTO;
import com.library_management.librarymanagement.Service.BorrowServ;

@Controller
@RequestMapping("borrow")
public class BorrowViewController {
    @Autowired
    private BorrowServ borrowServ;

    @Autowired
    private BookServ bookServ;

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping(path = "/add")
    public String addBorrow(@ModelAttribute BorrowSaveDTO borrowSaveDTO, RedirectAttributes redirectAttributes){
        borrowServ.addBorrow(borrowSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Borrow was succesfully created.");
        return "redirect:/admin/dashboard";
    }

    @PostMapping(path = "/admin/delete/{id}")
    public String removeBorrow(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        borrowServ.deleteBorrowByID(id);
        redirectAttributes.addFlashAttribute("message", "Borrow deleted succesfully");
        return "redirect:/admin/dashboard";
    }

    @PostMapping(path = "/admin/update/{id}")
    public String updateBorrow(@ModelAttribute BorrowUpdateDTO borrowUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        borrowServ.updateBorrow(borrowUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Borrow was succesfully updated.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/add")
    public String addBorrowFormAdmin(Model model) {
        model.addAttribute("borrow", new BorrowSaveDTO());
        model.addAttribute("users", userManagementService.getAllUsers());
        model.addAttribute("books", bookServ.getBooks());
        return "add_borrow";
    }


    @GetMapping(path = "/admin")
    public String getBorrowsAdmin(Model model){
        List<BorrowDTO> borrows =  borrowServ.getBorrows();
        model.addAttribute("borrows", borrows);
        return "borrows_list_admin";
    }

    @GetMapping(path = "/admin/update/{id}")
    public String updateBorrowForm(@PathVariable Long id, Model model) {
        BorrowDTO borrowDTO = borrowServ.getBorrowByID(id);
        model.addAttribute("borrow", borrowDTO);
        model.addAttribute("users", userManagementService.getAllUsers());
        model.addAttribute("books", bookServ.getBooks());
        return "update_borrow";
    }
}
