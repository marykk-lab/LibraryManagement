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

class AuthorServTest {
    @Mock
    private AuthorRep authorRep;

    @InjectMocks
    private AuthorServ authorServ;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddAuthor() {
        AuthorSaveDTO dto = new AuthorSaveDTO("Vojta Malinek", "wiki", "img");
        when(authorRep.save(any(Author.class))).thenReturn(null);

        String result = authorServ.addAuthor(dto);

        assertEquals("Author was added - Vojta Malinek", result);
    }

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

    @Test
    void testDeleteAuthorById() {
        when(authorRep.existsById(1L)).thenReturn(true);

        Long result = authorServ.deleteAuthorById(1L);

        assertEquals(1L, result);
    }

    @Test
    void testGetAuthors() {
        Author author = new Author("Vojta", "wiki", "img");
        author.setAuthorID(1L);
        when(authorRep.findAll()).thenReturn(List.of(author));

        ArrayList<AuthorDTO> authors = authorServ.getAuthors();

        assertEquals(1, authors.size());
        assertEquals("Vojta", authors.get(0).getName());
    }

    @Test
    void testGetAuthorByID() {
        Author author = new Author("Majvo", "wiki", "img");
        author.setAuthorID(2L);
        when(authorRep.existsById(2L)).thenReturn(true);
        when(authorRep.getReferenceById(2L)).thenReturn(author);

        AuthorDTO dto = authorServ.getAuthorByID(2L);

        assertEquals("Majvo", dto.getName());
    }

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