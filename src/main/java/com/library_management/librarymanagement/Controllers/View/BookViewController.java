package com.library_management.librarymanagement.Controllers.View;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library_management.librarymanagement.DTOs.BookDTO;
import com.library_management.librarymanagement.DTOs.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.BookUpdateDTO;
import com.library_management.librarymanagement.Service.BookServ;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("book")
public class BookViewController {
    @Autowired
    private BookServ bookServ;

    @PostMapping(path = "/admin/add")
    public String addBook(@ModelAttribute BookSaveDTO bookSaveDTO, RedirectAttributes redirectAttributes) {
        bookServ.addBook(bookSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully added");
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping(path = "/admin/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookServ.deleteBookById(id);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully deleted");
        return "redirect:/admin/dashboard";
    }

    @PostMapping(path = "/admin/update/{id}")
    public String updateBook(@ModelAttribute BookUpdateDTO bookUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookServ.updateBook(bookUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully updated");
        return "redirect:/admin/dashboard";
    }
    
    @GetMapping()
    public String getBooks(Model model) {
        List<BookDTO> books = bookServ.getBooks();
        model.addAttribute("books", books);
        return "books_list";
    }

    @GetMapping(path = "/admin/update/{id}")
    public String bookUpdateForm(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookServ.getBookByID(id);
        model.addAttribute("book", bookDTO);
        return "book_update";
    }
    
    @GetMapping(path = "/admin/add")
    public String addBookPage(Model model) {
        model.addAttribute("bookSaveDTO", new BookSaveDTO());
        return "book_add";
    }
       
}
