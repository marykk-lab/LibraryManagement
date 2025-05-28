package com.library_management.librarymanagement.Controllers.View;

import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import com.library_management.librarymanagement.Service.BookServ;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;

import com.library_management.librarymanagement.DTOs.Author.AuthorDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorUpdateDTO;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/author")
public class AuthorViewController {
    @Autowired
    private AuthorServ authorServ;

    @PostMapping(path = "/admin/add")
    public String addAuthor(@ModelAttribute AuthorSaveDTO authorSaveDTO, RedirectAttributes redirectAttributes){
        authorServ.addAuthor(authorSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully created.");
        return "redirect:/admin/dashboard";
    }
    @PostMapping(path = "/admin/delete/{id}")
    public String removeAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try{
            authorServ.deleteAuthorById(id);
            redirectAttributes.addFlashAttribute("message", "Author deleted succesfully");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Failed to delete author: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping(path = "/admin/update/{id}")
    public String updateAuthor(@ModelAttribute AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorServ.updateAuthor(authorUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully updated");
        return "redirect:/admin/dashboard";
    }

    @GetMapping(path = "/admin/add")
    public String addAuthorPage(Model model) {
        model.addAttribute("authorSaveDTO", new AuthorSaveDTO());
        return "add_author";
    }

    @GetMapping
    public String getAuthors(Model model){
        List<AuthorDTO> authors =  authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "authors_list";
    }

    @GetMapping("/admin")
    public String getAuthorsAdmin(Model model){
        List<AuthorDTO> authors =  authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "authors_list_admin";
    }

    @GetMapping(path = "/admin/update/{id}")
    public String authorUpdateForm(@PathVariable Long id, Model model) {
        AuthorDTO authorDTO = authorServ.getAuthorByID(id);
        model.addAttribute("author", authorDTO);
        return "update_author";
    }

    @GetMapping("/{id}")
    public String getAuthorDetails(@PathVariable Long id, Model model) {
        AuthorDTO authorDTO = authorServ.getAuthorByID(id);
        Set<Book> books = authorServ.getBooksById(id);
        model.addAttribute("books", books);
        model.addAttribute("author", authorDTO);
        return "author_details";
    }

    @GetMapping("/search")
    public String searchAuthors(@RequestParam("q") String query, Model model) {
        List<AuthorDTO> authors = authorServ.searchAuthorsByName(query);
        model.addAttribute("authors", authors);
        model.addAttribute("searchQuery", query);
        return "authors_list";
    }
}