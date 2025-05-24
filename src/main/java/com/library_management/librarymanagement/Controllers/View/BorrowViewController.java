package com.library_management.librarymanagement.Controllers.View;

import java.util.List;

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

    @PostMapping(path = "/admin/add")
    public String addBorrow(@ModelAttribute BorrowSaveDTO borrowSaveDTO, RedirectAttributes redirectAttributes){
        borrowServ.addBorrow(borrowSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Borrow was succesfully created.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/add")
    public String addBorrowPage() {
        return "add_borrow";
    }

    @GetMapping
    public String getBooks(Model model){
        List<BorrowDTO> borrows =  borrowServ.getBorrows();
        model.addAttribute("borrows", borrows);
        return "borrows_list";
    }

    @PostMapping(path = "/admin/delete/{id}")
    public String removeBorrow(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        borrowServ.deleteBorrowByID(id);
        redirectAttributes.addFlashAttribute("message", "Borrow deleted succesfully");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/update/{id}")
    public String borrowUpdateForm(@PathVariable Long id, Model model) {
        BorrowDTO borrowDTO = borrowServ.getBorrowByID(id);
        model.addAttribute("borrow", borrowDTO);
        return "borrow_update";
    }
}
