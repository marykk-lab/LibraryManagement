package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Author.AuthorDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorUpdateDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for {@link AuthorServ} that verifies the functionality of author management operations.
 * Uses Mockito for mocking dependencies.
 */
class AuthorServTest {
    /** Mock repository for author data access */
    @Mock
    private AuthorRep authorRep;

    /** The service being tested */
    @InjectMocks
    private AuthorServ authorServ;

    /**
     * Sets up the test environment before each test
     * Initializes mock objects using MockitoAnnotations
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the addition of a new author
     * Verifies that the service returns the correct confirmation message.
     */
    @Test
    void testAddAuthor() {
        AuthorSaveDTO dto = new AuthorSaveDTO("Vojta Malinek", "wiki", "img");
        when(authorRep.save(any(Author.class))).thenReturn(null);

        String result = authorServ.addAuthor(dto);

        assertEquals("Author was added - Vojta Malinek", result);
    }

    /**
     * Tests the author update functionality.
     * Verifies that an existing author can be updated with new information.
     */
    @Test
    void testUpdateAuthor() {
        Author author = new Author("Vojta", "wiki", "img");
        author.setAuthorID(1L);
        AuthorUpdateDTO dto = new AuthorUpdateDTO(1L, "Malinek", "newWiki", "newImg");
        when(authorRep.existsById(1L)).thenReturn(true);
        when(authorRep.getReferenceById(1L)).thenReturn(author);

        String result = authorServ.updateAuthor(dto);

        assertEquals("Author was updated - Malinek", result);
    }

    /**
     * Tests the deletion of an authorby id.
     * Verifies that the service returns the correct ID after successful deletion.
     */
    @Test
    void testDeleteAuthorById() {
        when(authorRep.existsById(1L)).thenReturn(true);

        Long result = authorServ.deleteAuthorById(1L);

        assertEquals(1L, result);
    }

    /**
     * Tests retrieval of all authors.
     * Verifies that the service correctly converts and returns author data.
     */
    @Test
    void testGetAuthors() {
        Author author = new Author("Vojta", "wiki", "img");
        author.setAuthorID(1L);
        when(authorRep.findAll()).thenReturn(List.of(author));

        ArrayList<AuthorDTO> authors = authorServ.getAuthors();

        assertEquals(1, authors.size());
        assertEquals("Vojta", authors.get(0).getName());
    }

    /**
     * Tests retrieval of a specific author by ID.
     * Verifies that the service returns the correct authordata.
     */
    @Test
    void testGetAuthorByID() {
        Author author = new Author("Majvo", "wiki", "img");
        author.setAuthorID(2L);
        when(authorRep.existsById(2L)).thenReturn(true);
        when(authorRep.getReferenceById(2L)).thenReturn(author);

        AuthorDTO dto = authorServ.getAuthorByID(2L);

        assertEquals("Majvo", dto.getName());
    }

    /**
     * Tests the author search functionality by name.
     * Verifies that the service correctly
     * finds and returns authors matching the search criteria.
     */
    @Test
    void testSearchAuthorsByName() {
        Author author = new Author("Vojta Malinek", "wiki", "img");
        author.setAuthorID(3L);
        when(authorRep.findByNameIgnoreCaseContaining("Vojta Malinek")).thenReturn(List.of(author));

        List<AuthorDTO> dtos = authorServ.searchAuthorsByName("Vojta Malinek");

        assertEquals(1, dtos.size());
        assertEquals("Vojta Malinek", dtos.get(0).getName());
    }
}