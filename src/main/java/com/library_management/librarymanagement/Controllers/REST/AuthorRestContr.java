package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.AuthorDTO;
import com.library_management.librarymanagement.DTOs.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.AuthorUpdateDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/author")
public class AuthorRestContr {
    @Autowired
    private AuthorServ authorServ;

    @PostMapping(path = "/admin")
    public String addAuthor(@RequestBody AuthorSaveDTO authorSaveDTO){
        return authorServ.addAuthor(authorSaveDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAuthors(){
        return authorServ.getAuthors();
    }

    @GetMapping(path = "/{id}")
    public AuthorDTO getAuthorByID(@PathVariable Long id){
        return authorServ.getAuthorByID(id);
    }

    @PutMapping(path = "/admin")
    public String updateAuthor(@RequestBody AuthorUpdateDTO authorUpdateDTO){
        return authorServ.updateAuthor(authorUpdateDTO);
    }

    @DeleteMapping(path = "/admin/{id}")
    public Long deleteAuthorById(@PathVariable Long id){
        return authorServ.deleteAuthorById(id);
    }

    @DeleteMapping(path = "/admin/{name}")
    public String deleteAuthorByName(@PathVariable String name){
        return authorServ.deleteAuthorByName(name);
    }

    @GetMapping(path = "/admin/{id}")
    public Set<Book> getBookById(@PathVariable Long id){
        return authorServ.getBooksById(id);
    }
}
