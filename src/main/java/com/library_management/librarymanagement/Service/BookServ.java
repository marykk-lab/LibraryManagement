package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.DTOs.Book.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.Book.BookUpdateDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import com.library_management.librarymanagement.Repositories.BookRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Service class for managing book-related operations in the library system.
 */
@Service
public class BookServ {
    @Autowired
    private BookRep bookRep;
    @Autowired
    private AuthorRep authorRep;

    /**
     * Adds a new book to the library system.
     *
     * @param bookSaveDTO DTO containing the book details to be saved
     * @return confirmation message with the title of added book
     * @throws IllegalArgumentException if book title is blank or empty
     */
    public String addBook(BookSaveDTO bookSaveDTO){
        String title = bookSaveDTO.getTitle();
        if (!title.isBlank()&&!title.isEmpty()){
            Author author = authorRep.getReferenceById(bookSaveDTO.getAuthorID());
            Book book = new Book(title, author, bookSaveDTO.getDescription(),
                    bookSaveDTO.getQuantity(), bookSaveDTO.getImageUrl());
            author.addBook(book);
            bookRep.save(book);
            return "Book was added - " + title;
        }
        throw new IllegalArgumentException("Book title is required!");
    }

    /**
     * Updates an existing book's details.
     *
     * @param bookUpdateDTO DTO containing the updated book information
     * @return confirmation message with the updated book's title
     * @throws IllegalArgumentException if book ID doesn't exist
     */
    public String updateBook(BookUpdateDTO bookUpdateDTO){
        if (bookRep.existsById(bookUpdateDTO.getBookID())) {
            Book book = bookRep.getReferenceById(bookUpdateDTO.getBookID());
            book.setTitle(bookUpdateDTO.getTitle());
            book.setAuthor(authorRep.getReferenceById(bookUpdateDTO.getAuthorID()));
            book.setDescription(bookUpdateDTO.getDescription());
            book.setQuantity(bookUpdateDTO.getQuantity());
            book.setImageUrl(bookUpdateDTO.getImageUrl());
            bookRep.save(book);
            return "Book was updated - " + book.getTitle();
        }
        throw new IllegalArgumentException("Book ID doesnt exist!");
    }

    /**
     * Retrieves all books from the library system.
     *
     * @return ArrayList of BookDTO objects representing all books
     */
    public ArrayList<BookDTO> getBooks(){
        List<Book> allBooks = bookRep.findAll();
        ArrayList<BookDTO> DTOBookArray = new ArrayList<>();
        for (Book book : allBooks){
            BookDTO DTOBook = new BookDTO(book.getBookID(), book.getTitle(),
                    book.getAuthor().getAuthorID(), book.getDescription(),
                    book.getQuantity(), book.getImageUrl());
            DTOBookArray.add(DTOBook);
        }
        return DTOBookArray;
    }

    /**
     * Deletes a book by its ID.
     *
     * @param ID the ID of the book to delete
     * @return the ID of the deleted book
     * @throws IllegalArgumentException if book ID doesn't exist
     */
    public Long deleteBookById(Long ID){
        if (bookRep.existsById(ID)){
            bookRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    /**
     * Deletes a book by its title.
     *
     * @param title the title of the book to delete
     * @return confirmation message with the deleted book's title
     * @throws IllegalArgumentException if book title doesn't exist
     */
    public String deleteBookByTitle(String title){
        List<Book> allBooks = bookRep.findAll();
        if (!title.isBlank()&&!title.isEmpty()){
            for (Book book : allBooks){
                if (book.getTitle().equals(title)){
                    bookRep.deleteById(book.getBookID());
                    return "Book was deleted - " + title;
                }
            }
        }
        throw new IllegalArgumentException("This title doesnt exist!");
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param ID the ID of the book to retrieve
     * @return BookDTO object containing the book's details
     * @throws IllegalArgumentException if book ID doesn't exist
     */
    public BookDTO getBookByID(Long ID){
        if (bookRep.existsById(ID)){
            Book book = bookRep.getReferenceById(ID);
            BookDTO bookDTO = new BookDTO(book.getBookID(), book.getTitle(),
                    book.getAuthor().getAuthorID(), book.getDescription(), book.getQuantity(),
                    book.getImageUrl());
            return bookDTO;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    /**
     * Retrieves all borrows associated with a specific book.
     *
     * @param ID the ID of the book
     * @return Set of Borrow objects associated with the book
     * @throws IllegalArgumentException if book ID doesn't exist
     */
    public Set<Borrow> getBorrowsById(Long ID){
        if (bookRep.existsById(ID)){
            Book book = bookRep.getReferenceById(ID);
            return book.getBorrows();
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    /**
     * Searches for books by title (case-insensitive, partial match).
     *
     * @param title the title or part of title to search for
     * @return List of BookDTO objects matching the search criteria
     */
    public List<BookDTO> searchBooksByTitle(String title) {
        List<Book> foundBooks = bookRep.findByTitleIgnoreCaseContaining(title);
        List<BookDTO> result = new ArrayList<>();
        for (Book book : foundBooks) {
            result.add(new BookDTO(
                    book.getBookID(), book.getTitle(), book.getAuthor().getAuthorID(),
                    book.getDescription(), book.getQuantity(), book.getImageUrl()
            ));
        }
        return result;
    }

}