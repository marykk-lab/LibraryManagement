package com.library_management.librarymanagement.Controllers;

import com.library_management.librarymanagement.DTOs.*;
import com.library_management.librarymanagement.Service.AuthorServ;
import com.library_management.librarymanagement.Service.BookServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/book")
public class BookRestContr {
    @Autowired
    private BookServ bookServ;

    @PostMapping(path = "/add_book")
    public String addBook(@RequestBody BookSaveDTO bookSaveDTO){
        String title = bookServ.addBook(bookSaveDTO);
        return "Successfully added";
    }

    @GetMapping(path = "/get_all_books")
    public List<BookDTO> getAuthors(){
        return bookServ.getBooks();
    }

    @PutMapping(path = "/update_book")
    public String updateAuthor(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookServ.updateBook(bookUpdateDTO);
    }

    @DeleteMapping(path = "/delete_book_by_id/{id}")
    public Long deleteAuthorById(@PathVariable(value = "id")Long id){
        return bookServ.deleteBookById(id);
    }

    @DeleteMapping(path = "/delete_book_by_title")
    public String deleteAuthorByName(@RequestParam String title){
        return bookServ.deleteBookByTitle(title);
    }
}
