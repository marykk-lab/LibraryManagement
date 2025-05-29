package com.library_management.librarymanagement.Controllers.View;


import com.library_management.librarymanagement.Entities.Book;
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


/**
 * Controller responsible for handling web requests related to Author management.
 * Provides endpoints for both admin and regular user operations on authors.
 */
@Controller
@RequestMapping("/author")
public class AuthorViewController {
    @Autowired
    private AuthorServ authorServ;

    /**
     * Handles the creation of a new author.
     *
     * @param authorSaveDTO DTO containing the author information to be saved
     * @param redirectAttributes used to flash messages after redirect
     * @return redirects to admin dashboard with success message
     */
    @PostMapping(path = "/admin/add")
    public String addAuthor(@ModelAttribute AuthorSaveDTO authorSaveDTO, RedirectAttributes redirectAttributes) {
        authorServ.addAuthor(authorSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully created.");
        return "redirect:/admin/dashboard";
    }

    /**
     * Handles the deletion of an author by their ID.
     *
     * @param id the ID of the author to be deleted
     * @param redirectAttributes used to flash messages after redirect
     * @return redirects to admin dashboard with success/error message
     */
    @PostMapping(path = "/admin/delete/{id}")
    public String removeAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            authorServ.deleteAuthorById(id);
            redirectAttributes.addFlashAttribute("message", "Author deleted succesfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Failed to delete author: " + e.getMessage());
        }
        return "redirect:/admin/dashboard";
    }

    /**
     * Handles the update of an existing author's information.
     *
     * @param authorUpdateDTO DTO containing the updated author information
     * @param id the ID of the author to be updated
     * @param redirectAttributes used to flash messages after redirect
     * @return redirects to admin dashboard with succes message
     */
    @PostMapping(path = "/admin/update/{id}")
    public String updateAuthor(@ModelAttribute AuthorUpdateDTO authorUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorServ.updateAuthor(authorUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Author succesfully updated");
        return "redirect:/admin/dashboard";
    }

    /**
     * Displays the form for adding a new author.
     *
     * @param model the Model object to add attributes
     * @return the name of the add author view template
     */
    @GetMapping(path = "/admin/add")
    public String addAuthorPage(Model model) {
        model.addAttribute("authorSaveDTO", new AuthorSaveDTO());
        return "add_author";
    }

    /**
     * Retrieves and displays the list of all authors for regular users.
     *
     * @param model the Model object to add attributes
     * @return the name of the authors list view template
     */
    @GetMapping
    public String getAuthors(Model model) {
        List<AuthorDTO> authors = authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "authors_list";
    }

    /**
     * Retrieves and displays the list of all authors for admin users.
     *
     * @param model the Model object to add attributes
     * @return the name of the admin authors list view template
     */
    @GetMapping("/admin")
    public String getAuthorsAdmin(Model model) {
        List<AuthorDTO> authors = authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "authors_list_admin";
    }

    /**
     * Displays the form for updating an existing author's information.
     *
     * @param id the ID of the author to be updated
     * @param model the Model object to add attributes
     * @return the name of the update author view template
     */
    @GetMapping(path = "/admin/update/{id}")
    public String authorUpdateForm(@PathVariable Long id, Model model) {
        AuthorDTO authorDTO = authorServ.getAuthorByID(id);
        model.addAttribute("author", authorDTO);
        return "update_author";
    }

    /**
     * Displays detailed information about a specific author and their books.
     *
     * @param id the ID of the author to display
     * @param model the Model object to add attributes
     * @return the name of the author details view template
     */
    @GetMapping("/{id}")
    public String getAuthorDetails(@PathVariable Long id, Model model) {
        AuthorDTO authorDTO = authorServ.getAuthorByID(id);
        Set<Book> books = authorServ.getBooksById(id);
        model.addAttribute("books", books);
        model.addAttribute("author", authorDTO);
        return "author_details";
    }

    /**
     * Searches for authors by name and displays the results.
     *
     * @param query the search query string
     * @param model the Model object to add attributes
     * @return the name of the authors list view template with search results
     */
    @GetMapping("/search")
    public String searchAuthors(@RequestParam("q") String query, Model model) {
        List<AuthorDTO> authors = authorServ.searchAuthorsByName(query);
        model.addAttribute("authors", authors);
        model.addAttribute("searchQuery", query);
        return "authors_list";
    }
}