package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.BookDTO;
import com.library_management.librarymanagement.DTOs.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.BookUpdateDTO;
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

    public String addBook(BookSaveDTO bookSaveDTO){
        String title = bookSaveDTO.getTitle();
        if (!title.isBlank()&&!title.isEmpty()){
            Book book = new Book(title, authorRep.getReferenceById(bookSaveDTO.getAuthorID()));
            bookRep.save(book);
            return title;
        }
        throw new IllegalArgumentException("Book title is required!");
    }

    public String updateBook(BookUpdateDTO bookUpdateDTO){
        if (bookRep.existsById(bookUpdateDTO.getBookID())) {
            Book book = bookRep.getReferenceById(bookUpdateDTO.getBookID());
            book.setTitle(bookUpdateDTO.getTitle());
            book.setAuthor(authorRep.getReferenceById(bookUpdateDTO.getAuthorID()));
            bookRep.save(book);
            return book.getTitle();
        }
        throw new IllegalArgumentException("Book ID doesnt exist!");
    }

    public ArrayList<BookDTO> getBooks(){
        List<Book> allBooks = bookRep.findAll();
        ArrayList<BookDTO> DTOBookArray = new ArrayList<>();
        for (Book book : allBooks){
            BookDTO DTOBook = new BookDTO(book.getBookID(), book.getTitle(), book.getAuthor());
            DTOBookArray.add(DTOBook);
        }
        return DTOBookArray;
    }

    public Long deleteBookById(Long ID){
        if (bookRep.existsById(ID)){
            bookRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public String deleteBookByTitle(String title){
        List<Book> allBooks = bookRep.findAll();
        if (!title.isBlank()&&!title.isEmpty()){
            for (Book book : allBooks){
                if (book.getTitle().equals(title)){
                    authorRep.deleteById(book.getBookID());
                    return title;
                }
            }
        }
        throw new IllegalArgumentException("This title doesnt exist!");
    }
}
