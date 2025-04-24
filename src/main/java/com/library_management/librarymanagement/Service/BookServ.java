package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.BookDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import com.library_management.librarymanagement.Repositories.BookRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServ {
    @Autowired
    private BookRep bookRep;
    @Autowired
    private AuthorRep authorRep;

    public ArrayList<BookDTO> getBooks(){
        List<Book> allBooks = bookRep.findAll();
        ArrayList<BookDTO> bookDTOArray = new ArrayList<>();
        for (Book book : allBooks){
            BookDTO bookDTO = new BookDTO(book.getBookID(), book.getTitle(), book.getAuthor());
            bookDTOArray.add(bookDTO);
        }
        return bookDTOArray;
    }
}
