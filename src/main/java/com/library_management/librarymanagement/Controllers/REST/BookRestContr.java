package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.DTOs.Book.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.Book.BookUpdateDTO;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Service.BookServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public BookDTO getBookByID(@PathVariable Long id){
        return bookServ.getBookByID(id);
    }
    @GetMapping(path = "/admin/{id}")
    public Set<Borrow> getBorrowsById(@PathVariable Long id){
        return bookServ.getBorrowsById(id);
    }
    @PutMapping(path = "/admin")
    public String updateBook(@RequestBody BookUpdateDTO bookUpdateDTO){
        return bookServ.updateBook(bookUpdateDTO);
    }

    @DeleteMapping(path = "/admin/{id}")
    public Long deleteBookById(@PathVariable Long id){
        return bookServ.deleteBookById(id);
    }

    @DeleteMapping(path = "/admin/{title}")
    public String deleteBookByTitle(@PathVariable String title){
        return bookServ.deleteBookByTitle(title);
    }


}
