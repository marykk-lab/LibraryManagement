package com.library_management.librarymanagement.Controllers;

import com.library_management.librarymanagement.DTOs.AuthorDTO;
import com.library_management.librarymanagement.DTOs.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.AuthorUpdateDTO;
import com.library_management.librarymanagement.Service.AuthorServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/author")
public class AuthorRestContr {
    @Autowired
    private AuthorServ authorServ;

    @PostMapping(path = "/add_author")
    public String addAuthor(@RequestBody AuthorSaveDTO authorSaveDTO){
        String name = authorServ.addAuthor(authorSaveDTO);
        return "Successfully added";
    }

    @GetMapping(path = "/get_all_authors")
    public List<AuthorDTO> getAuthors(){
        return authorServ.getAuthors();
    }

    @PutMapping(path = "/update_author")
    public String updateAuthor(@RequestBody AuthorUpdateDTO authorUpdateDTO){
        return authorServ.updateAuthor(authorUpdateDTO);
    }

    @DeleteMapping(path = "/delete_author_by_id/{id}")
    public Long deleteAuthorById(@PathVariable(value = "id")Long id){
        return authorServ.deleteAuthorById(id);
    }

    @DeleteMapping(path = "/delete_author_by_name")
    public String deleteAuthorByName(@RequestParam String name){
        return authorServ.deleteAuthorByName(name);
    }
}
