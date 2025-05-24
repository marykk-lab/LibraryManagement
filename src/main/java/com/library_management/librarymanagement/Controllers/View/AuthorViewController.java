package com.library_management.librarymanagement.Controllers.View;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.library_management.librarymanagement.DTOs.AuthorDTO;
import com.library_management.librarymanagement.DTOs.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.AuthorUpdateDTO;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("author")
public class AuthorViewController {
    @Autowired
    private AuthorServ authorServ;

    @PostMapping(path = "/admin/add")
    public String addAuthor(@ModelAttribute AuthorSaveDTO authorSaveDTO, RedirectAttributes redirectAttributes){
        authorServ.addAuthor(authorSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully created.");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/add")
    public String addAuthorPage() {
        return "add_author";
    }

    @GetMapping
    public String getAuthors(Model model){
        List<AuthorDTO> authors =  authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "authors_list";
    }

    @PostMapping(path = "/admin/delete/{id}")
    public String removeAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorServ.deleteAuthorById(id);
        redirectAttributes.addFlashAttribute("message", "Author deleted succesfully");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/update/{id}")
    public String authorUpdateForm(@PathVariable Long id, Model model) {
        AuthorDTO authorDTO = authorServ.getAuthorByID(id);
        model.addAttribute("author", authorDTO);
        return "author_update";
    }
    @PostMapping(path = "/admin/update/{id}")
    public String updateAuthor(@ModelAttribute AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorServ.updateAuthor(authorUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully updated");
        return "redirect:/admin/dashboard";
    }
}