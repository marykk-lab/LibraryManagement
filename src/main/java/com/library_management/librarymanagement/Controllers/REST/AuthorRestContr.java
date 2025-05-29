package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.Author.AuthorDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorUpdateDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Rest Controller for managing author-related operations in the library management system.
 * Handles API endpoints for creating, retrieving, updating, and deleting authors.
 */
@RestController
@RequestMapping("api/author")
public class AuthorRestContr {
    @Autowired
    private AuthorServ authorServ;

    /**
     * Adds a new author to the library management system.
     *
     * @param authorSaveDTO the data transfer object containing the details of the author to be added
     * @return a confirmation message indicating the successful addition of the author
     */
    @PostMapping(path = "/admin")
    public String addAuthor(@RequestBody AuthorSaveDTO authorSaveDTO){
        return authorServ.addAuthor(authorSaveDTO);
    }

    /**
     * Retrieves a list of all authors in the library management system.
     *
     * @return a list of AuthorDTO objects representing the authors
     */
    @GetMapping
    public List<AuthorDTO> getAuthors(){
        return authorServ.getAuthors();
    }

    /**
     * Retrieves an author's details based on their unique identifier.
     *
     * @param id the unique identifier of the author to be retrieved
     * @return an AuthorDTO object containing the details of the author
     * @throws IllegalArgumentException if the specified id does not exist
     */
    @GetMapping(path = "/{id}")
    public AuthorDTO getAuthorByID(@PathVariable Long id){
        return authorServ.getAuthorByID(id);
    }

    /**
     * Retrieves a set of books associated with the specified author ID.
     *
     * @param id the unique identifier of the author whose books are to be retrieved
     * @return a set of Book objects associated with the specified author ID
     * @throws IllegalArgumentException if the specified ID does not exist
     */
    @GetMapping(path = "/admin/{id}")
    public Set<Book> getBookById(@PathVariable Long id){
        return authorServ.getBooksById(id);
    }

    /**
     * Updates the details of an existing author in the library management system.
     *
     * @param authorUpdateDTO the data transfer object containing the updated details of the author
     * @return a confirmation message indicating the successful update of the author
     * @throws IllegalArgumentException if the specified author ID does not exist
     */
    @PutMapping(path = "/admin")
    public String updateAuthor(@RequestBody AuthorUpdateDTO authorUpdateDTO){
        return authorServ.updateAuthor(authorUpdateDTO);
    }

    /**
     * Deletes an author from the system using their unique identifier.
     *
     * @param id the unique identifier of the author to be deleted
     * @return the ID of the deleted author
     * @throws IllegalArgumentException if the specified author ID does not exist
     */
    @DeleteMapping(path = "/admin/{id}")
    public Long deleteAuthorById(@PathVariable Long id){
        return authorServ.deleteAuthorById(id);
    }

    /**
     * Deletes an author from the system using their name.
     *
     * @param name the name of the author to be deleted
     * @return a confirmation message indicating the successful deletion of the author
     * @throws IllegalArgumentException if no author with the specified name exists
     */
    @DeleteMapping(path = "/admin/{name}")
    public String deleteAuthorByName(@PathVariable String name){
        return authorServ.deleteAuthorByName(name);
    }


}
