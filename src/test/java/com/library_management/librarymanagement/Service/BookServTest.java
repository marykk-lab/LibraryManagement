package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.DTOs.Book.BookSaveDTO;
import com.library_management.librarymanagement.DTOs.Book.BookUpdateDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import com.library_management.librarymanagement.Repositories.BookRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServTest {
    @Mock
    private BookRep bookRep;
    @Mock
    private AuthorRep authorRep;

    @InjectMocks
    private BookServ bookServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBook() {
        BookSaveDTO dto = new BookSaveDTO("Book Title", 1L, "Description", 1, "img");
        Author author = new Author("Vojta", "wiki", "img");
        author.setAuthorID(1L);

        when(authorRep.getReferenceById(1L)).thenReturn(author);
        when(bookRep.save(any(Book.class))).thenReturn(null);

        String result = bookServ.addBook(dto);

        assertEquals("Book was added - Book Title", result);
    }


    @Test
    void testUpdateBook() {
        BookUpdateDTO dto = new BookUpdateDTO(1L, "Updated Title", 1L, "descriptiond", 4, "img");
        Book book = mock(Book.class);
        Author author = new Author("Author", "wiki", "img");
        author.setAuthorID(1L);

        when(bookRep.existsById(1L)).thenReturn(true);
        when(bookRep.getReferenceById(1L)).thenReturn(book);
        when(authorRep.getReferenceById(1L)).thenReturn(author);

        String result = bookServ.updateBook(dto);

        assertEquals("Book was updated - null", result);
        verify(bookRep).save(book);
    }


    @Test
    void testGetBooks() {
        Author author = new Author("author", "wiki", "img");
        author.setAuthorID(1L);
        Book book = new Book("title", author, "description", 5, "img");
        book.setBookID(1L);

        when(bookRep.findAll()).thenReturn(List.of(book));

        ArrayList<BookDTO> books = bookServ.getBooks();

        assertEquals(1, books.size());
        assertEquals("title", books.get(0).getTitle());
    }


    @Test
    void testDeleteBookById() {
        when(bookRep.existsById(1L)).thenReturn(true);

        Long result = bookServ.deleteBookById(1L);

        assertEquals(1L, result);
        verify(bookRep).deleteById(1L);
    }


    @Test
    void testDeleteBookByTitle() {
        Author author = new Author("author", "wiki", "img");
        author.setAuthorID(1L);
        Book book = new Book("title", author, "description", 3, "img");
        book.setBookID(1L);

        when(bookRep.findAll()).thenReturn(List.of(book));

        String result = bookServ.deleteBookByTitle("title");

        assertEquals("Book was deleted - title", result);
        verify(bookRep).deleteById(1L);
    }

    @Test
    void testGetBookByID() {
        Author author = new Author("author", "wiki", "img");
        author.setAuthorID(1L);
        Book book = new Book("title", author, "defeq-kf", 3, "img");
        book.setBookID(1L);

        when(bookRep.existsById(1L)).thenReturn(true);
        when(bookRep.getReferenceById(1L)).thenReturn(book);

        BookDTO dto = bookServ.getBookByID(1L);

        assertEquals("title", dto.getTitle());
    }


    @Test
    void testGetBorrowsById() {
        Book book = mock(Book.class);
        Set<Borrow> borrowSet = new HashSet<>();
        when(bookRep.existsById(1L)).thenReturn(true);
        when(bookRep.getReferenceById(1L)).thenReturn(book);
        when(book.getBorrows()).thenReturn(borrowSet);

        Set<Borrow> result = bookServ.getBorrowsById(1L);

        assertSame(borrowSet, result);
    }


    @Test
    void testSearchBooksByTitle() {
        Author author = new Author("Vojta", "wiki", "img");
        author.setAuthorID(1L);
        Book book = new Book("PROTEIN", author, "description", 3, "img");
        book.setBookID(1L);

        when(bookRep.findByTitleIgnoreCaseContaining("PROTEIN")).thenReturn(List.of(book));

        List<BookDTO> dtos = bookServ.searchBooksByTitle("PROTEIN");

        assertEquals(1, dtos.size());
        assertEquals("PROTEIN", dtos.get(0).getTitle());
    }

}