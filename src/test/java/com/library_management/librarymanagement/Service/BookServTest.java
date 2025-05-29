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

/**
 * Test class for {@link BookServ} that verifies the functionality of book management operations.
 * Uses Mockito for mocking dependencies.
 */
class BookServTest {
    /** Mock repository for book data access */
    @Mock
    private BookRep bookRep;

    /** Mock repository for author data access */
    @Mock
    private AuthorRep authorRep;

    /** The service being tested */
    @InjectMocks
    private BookServ bookServ;

    /**
     * Sets up the test environment before each test.
     * Initializes mock objects using MockitoAnnotations.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the addition of a new book.
     * Verifies that the service returns the correct confirmation message
     * when adding a book with a valid author reference.
     */
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

    /**
     * Tests the book update functionality.
     * Verifies that an existing book can be updated with new information
     * and the correct confirmation message is returned.
     */
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

    /**
     * Tests retrieval of all books.
     * Verifies that the service correctly converts and returns book data
     * including associated author information.
     */
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

    /**
     * Tests the deletioning of a book by ID.
     * Verifies that the service returns the correct ID after successful deletion.
     */
    @Test
    void testDeleteBookById() {
        when(bookRep.existsById(1L)).thenReturn(true);

        Long result = bookServ.deleteBookById(1L);

        assertEquals(1L, result);
        verify(bookRep).deleteById(1L);
    }

    /**
     * Tests the deletion of a book by title.
     * Verifies that the service correctly identifies and deletes the book,
     * returning appropriate confirmation message.
     */
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

    /**
     * Tests retrieval of a specific book by ID.
     * Verifies that the service returns the correct book data
     * when requestsg an egxisting book.
     */
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

    /**
     * Tests retrieval of borrows for a specific book.
     * Verifies that thdqe service correctly returns the set of borrows
     * associated with the specified book ID.
     */
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

    /**
     * Tests the book search functionality by title.
     * Verifies that the service correctly finds and returns books
     * matching the search criteriad
     */
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