package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Borrow.BorrowDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowUpdateDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.BookRep;
import com.library_management.librarymanagement.Repositories.BorrowRep;
import com.library_management.librarymanagement.Repositories.UserRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link BorrowServ} that verifies the functionality of borrowing operations.
 * Uses Mockito for mocking dependencies and testing the service layer logic.
 */
class BorrowServTest {
    /** Mock repository for user data access */
    @Mock
    private UserRep userRep;

    /** Mock repository for borrow data access */
    @Mock
    private BorrowRep borrowRep;

    /** Mock repository for book data access */
    @Mock
    private BookRep bookRep;

    /** The service being tested */
    @InjectMocks
    private BorrowServ borrowServ;

    /**
     * Sets up the test environment before each test.
     * Initializes mock objects using MockitoAnnotations.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creation of a new borrow record.
     * Verifies that:
     * - The book quantity is decremented
     * - The borrow is added to both user and book
     * - The borrow is saved in the repository
     */
    @Test
    void testAddBorrow() {
        BorrowSaveDTO dto = new BorrowSaveDTO(1L, 2L, LocalDate.now(), LocalDate.now().plusDays(7));
        Book book = mock(Book.class);
        when(book.getQuantity()).thenReturn(1);
        User user = mock(User.class);

        when(bookRep.getReferenceById(1L)).thenReturn(book);
        when(userRep.getReferenceById(2L)).thenReturn(user);

        Long result = borrowServ.addBorrow(dto);

        assertEquals(1L, result);
        verify(book).minusBook();
        verify(user).addBorrow(any(Borrow.class));
        verify(book).addBorrow(any(Borrow.class));
        verify(borrowRep).save(any(Borrow.class));
    }

    /**
     * Tests retrieval of all borrow records.
     * Verifies that the service correctly converts and returns borrow data
     * including associated book and user information.
     */
    @Test
    void testGetBorrows() {
        Borrow borrow = mock(Borrow.class);
        when(borrow.getBorrowID()).thenReturn(1L);
        when(borrow.getBorrowingDate()).thenReturn(LocalDate.now());
        when(borrow.getReturnDate()).thenReturn(LocalDate.now().plusDays(7));
        Book book = mock(Book.class);
        when(book.getBookID()).thenReturn(1L);
        when(book.getTitle()).thenReturn("Title");
        when(borrow.getBook()).thenReturn(book);
        User user = mock(User.class);
        when(user.getUserID()).thenReturn(2L);
        when(user.getUsername()).thenReturn("test");
        when(borrow.getUser()).thenReturn(user);

        when(borrowRep.findAll()).thenReturn(List.of(borrow));
        when(borrowRep.getReferenceById(1L)).thenReturn(borrow);

        ArrayList<BorrowDTO> result = borrowServ.getBorrows();

        assertEquals(1, result.size());
        assertEquals("Title", result.get(0).getBookTitle());
        assertEquals("test", result.get(0).getUsername());
    }

    /**
     * Tests the deletion of a borrow record by ID.
     * Verifies that:
     * - The book quantity is incremented back
     * - The borrow record is deleted from the repository
     */
    @Test
    void testDeleteBorrowByID() {
        Borrow borrow = mock(Borrow.class);
        Book book = mock(Book.class);
        when(borrow.getBook()).thenReturn(book);
        when(borrow.getBorrowID()).thenReturn(10L);
        when(book.getBookID()).thenReturn(1L);

        when(borrowRep.existsById(10L)).thenReturn(true);
        when(borrowRep.getReferenceById(10L)).thenReturn(borrow);
        when(bookRep.getReferenceById(1L)).thenReturn(book);

        Long result = borrowServ.deleteBorrowByID(10L);

        assertEquals(10L, result);
        verify(book).plusBook();
        verify(borrowRep).deleteById(10L);
    }

    /**
     * Tests retrieval of a specific borrow record by ID.
     * Verifies that the service returns the correct borrow data
     * including associated book title and username.
     */
    @Test
    void testGetBorrowByID() {
        Borrow borrow = mock(Borrow.class);
        when(borrow.getBorrowID()).thenReturn(3L);
        when(borrow.getBorrowingDate()).thenReturn(LocalDate.now());
        when(borrow.getReturnDate()).thenReturn(LocalDate.now().plusDays(2));
        Book book = mock(Book.class);
        when(book.getBookID()).thenReturn(1L);
        when(book.getTitle()).thenReturn("title");
        User user = mock(User.class);
        when(user.getUserID()).thenReturn(2L);
        when(user.getUsername()).thenReturn("test");
        when(borrow.getBook()).thenReturn(book);
        when(borrow.getUser()).thenReturn(user);

        when(borrowRep.existsById(3L)).thenReturn(true);
        when(borrowRep.getReferenceById(3L)).thenReturn(borrow);

        BorrowDTO dto = borrowServ.getBorrowByID(3L);

        assertEquals(3L, dto.getBorrowID());
        assertEquals("title", dto.getBookTitle());
        assertEquals("test", dto.getUsername());
    }

    /**
     * Tests the update functionality of a borrow record.
     * Verifies that:
     * - The book and user references are updated
     * - The borrowing and return dates are updated
     * - The updated borrow is saved in the repository
     */
    @Test
    void testUpdateBorrow() {
        BorrowUpdateDTO dto = new BorrowUpdateDTO(5L, 1L, "Title", 2L, LocalDate.now(), LocalDate.now().plusDays(1));
        Borrow borrow = mock(Borrow.class);

        when(borrowRep.existsById(5L)).thenReturn(true);
        when(borrowRep.getReferenceById(5L)).thenReturn(borrow);

        Book book = mock(Book.class);
        when(bookRep.getReferenceById(1L)).thenReturn(book);
        User user = mock(User.class);
        when(userRep.getReferenceById(2L)).thenReturn(user);

        String result = borrowServ.updateBorrow(dto);

        assertEquals("Borrow was updated!", result);
        verify(borrow).setBook(book);
        verify(borrow).setUser(user);
        verify(borrow).setBorrowingDate(dto.getBorrowingDate());
        verify(borrow).setReturnDate(dto.getReturnDate());
        verify(borrowRep).save(borrow);
    }
}