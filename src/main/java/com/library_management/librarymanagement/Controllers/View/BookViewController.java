package com.library_management.librarymanagement.Controllers.View;

import java.util.List;

import com.library_management.librarymanagement.DTOs.Author.AuthorDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.DTOs.Book.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.Book.BookUpdateDTO;
import com.library_management.librarymanagement.Service.BookServ;

@Controller
@RequestMapping("book")
public class BookViewController {
    @Autowired
    private BookServ bookServ;
    @Autowired
    private AuthorServ authorServ;

    /**
     * Adds a new book to the system and redirects to the admin dashboard upon successful addition.
     *
     * @param bookSaveDTO          Data Transfer Object containing the details of the book to add.
     * @param redirectAttributes   Attributes used to pass flash messages during redirects.
     * @return A string representing the redirect URL to the admin dashboard.
     */
    @PostMapping(path = "/admin/add")
    public String addBook(@ModelAttribute BookSaveDTO bookSaveDTO, RedirectAttributes redirectAttributes) {
        bookServ.addBook(bookSaveDTO);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully added");
        return "redirect:/admin/dashboard";
    }

    /**
     * Handles the deletion of a book by its ID and redirects to the admin dashboard.
     *
     * @param id the ID of the book to be deleted
     * @param redirectAttributes the attributes to add flash messages for the redirect
     * @return a redirect URL to the admin dashboard
     */
    @PostMapping(path = "/admin/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookServ.deleteBookById(id);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully deleted");
        return "redirect:/admin/dashboard";
    }


    /**
     * Updates the details of an existing book based on the provided data.
     *
     * @param bookUpdateDTO an object containing the updated book information
     * @param id the unique identifier of the book to be updated
     * @param redirectAttributes attributes for flash messaging, used to send success messages to the view
     * @return a redirect to the book administration page upon successful update
     */
    @PostMapping(path = "/admin/update/{id}")
    public String updateBook(@ModelAttribute BookUpdateDTO bookUpdateDTO, @PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookServ.updateBook(bookUpdateDTO);
        redirectAttributes.addFlashAttribute("message", "Book was succesfully updated");
        return "redirect:/book/admin";
    }

    /**
     * Handles the HTTP GET request to retrieve a list of books and prepares the model with this data
     * for rendering a view template.
     *
     * @param model the data model used to pass attributes to the view
     * @return the name of the view template (books_list) to be rendered
     */
    @GetMapping()
    public String getBooks(Model model) {
        List<BookDTO> books = bookServ.getBooks();
        model.addAttribute("books", books);
        return "books_list";
    }

    /**
     * Handles the GET request at the "/admin" endpoint to retrieve a list of books and authors
     * for display on the admin page. Populates the model with the retrieved data.
     *
     * @param model the {@link Model} object that holds the data to be passed to the view
     * @return the name of the Thymeleaf template to be rendered ("books_list_admin")
     */
    @GetMapping(path = "/admin")
    public String getBooksAdmin(Model model) {
        List<BookDTO> books = bookServ.getBooks();
        model.addAttribute("books", books);
        List<AuthorDTO> authors = authorServ.getAuthors();
        model.addAttribute("authors", authors);
        return "books_list_admin";
    }

    /**
     * Handles the request to display the "update book" form for a specific book.
     * Fetches the book details and available authors to populate the form.
     *
     * @param id the ID of the book to be updated
     * @param model the model object used to pass attributes to the view
     * @return the name of the view template for updating a book
     */
    @GetMapping(path = "/admin/update/{id}")
    public String updateBookForm(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookServ.getBookByID(id);
        model.addAttribute("book", bookDTO);
        model.addAttribute("authors", authorServ.getAuthors());
        return "book_update";
    }

    /**
     * Handles GET requests and prepares the form for adding a new book.
     * Adds attributes to the model for rendering the form.
     *
     * @param model themodel object used to add attributes for the view layer
     * @return the name of the view to be returned "add_book"
     */
    @GetMapping(path = "/admin/add")
    public String addBookForm(Model model) {
        model.addAttribute("bookSaveDTO", new BookSaveDTO());
        model.addAttribute("authors", authorServ.getAuthors());
        return "add_book";
    }

    /**
     * Handles the request to fetch and display the details of a specific book.
     *
     * @param id the unique identifier of the book whose details are to be retrieved
     * @param model the model object used to pass attributes to the view
     * @return the name of the view template to display the book's details
     */
    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookServ.getBookByID(id);
        BorrowSaveDTO borrowRequest = new BorrowSaveDTO();
        borrowRequest.setBookID(bookDTO.getBookID());
        model.addAttribute("authors", authorServ.getAuthors());
        model.addAttribute("book", bookDTO);
        model.addAttribute("borrowRequest", borrowRequest);
        return "book_details";
    }

    /**
     * Handles a search request for books by title and returns the view containing the list of matching books.
     * The search query is obtained from the request paramevter and the resulting list of books is added to the model.
     *
     * @author ChatGPT helped to make this method.
     * @param query the search query string submitted by the user to find books by title.
     * @param model the model object used to pass attributes (e.g., search results and the query) to the view.
     * @return the name of the view template ("books_list") to render the search results.
     */
    @GetMapping("/search")
    public String searchBooks(@RequestParam("q") String query, Model model) {
        List<BookDTO> books = bookServ.searchBooksByTitle(query);
        model.addAttribute("books", books);
        model.addAttribute("searchQuery", query);
        return "books_list";
    }
}
