package com.library_management.librarymanagement.Controllers;

import com.library_management.librarymanagement.DTOs.*;
import com.library_management.librarymanagement.Entities.Book;
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
        return bookServ.addBook(bookSaveDTO);
    }

    @GetMapping(path = "/get_all_books")
    public List<BookDTO> getBooks(){
        return bookServ.getBooks();
    }

    @GetMapping(path = "/get_book_by_id/{id}")
    public BookDTO getBookByID(@PathVariable(value = "id")Long id){
        return bookServ.getBookByID(id);
    }

    @PutMapping(path = "/update_book")
    public String updateBook(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookServ.updateBook(bookUpdateDTO);
    }

    @DeleteMapping(path = "/delete_book_by_id/{id}")
    public Long deleteBookById(@PathVariable(value = "id")Long id){
        return bookServ.deleteBookById(id);
    }

    @DeleteMapping(path = "/delete_book_by_title")
    public String deleteBookByTitle(@RequestParam String title){
        return bookServ.deleteBookByTitle(title);
    }
}
