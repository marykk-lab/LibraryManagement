package com.library_management.librarymanagement.Controllers.REST;

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

    @PostMapping(path = "/admin")
    public String addBook(@RequestBody BookSaveDTO bookSaveDTO){
        return bookServ.addBook(bookSaveDTO);
    }

    @GetMapping
    public List<BookDTO> getBooks(){
        return bookServ.getBooks();
    }

    @GetMapping(path = "/{id}")
    public BookDTO getBookByID(@PathVariable(value = "id")Long id){
        return bookServ.getBookByID(id);
    }

    @PutMapping(path = "/admin")
    public String updateBook(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookServ.updateBook(bookUpdateDTO);
    }

    @DeleteMapping(path = "/admin/{id}")
    public Long deleteBookById(@PathVariable(value = "id")Long id){
        return bookServ.deleteBookById(id);
    }

    @DeleteMapping(path = "/admin/{name}")
    public String deleteBookByTitle(@PathVariable(value = "title")String title){
        return bookServ.deleteBookByTitle(title);
    }
}
