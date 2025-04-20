package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.AuthorDTO;
import com.library_management.librarymanagement.DTOs.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.AuthorUpdateDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorServ{
    @Autowired
    private AuthorRep authorRep;

    public String addAuthor(AuthorSaveDTO authorSaveDTO) {
         String name = authorSaveDTO.getName();
         if (name!=null && !name.isBlank()){
             Author newAuthor = new Author(name);
             authorRep.save(newAuthor);
             return name;
         }
         throw new IllegalArgumentException("Author name is required!");
    }

    public String updateAuthor(AuthorUpdateDTO authorUpdateDTO) {
        if (authorRep.existsById(authorUpdateDTO.getAuthorID())) {
            Author author = authorRep.getById(authorUpdateDTO.getAuthorID());
            author.setName(authorUpdateDTO.getName());
            authorRep.save(author);
            return author.getName();
        }
        throw new IllegalArgumentException("Author ID doesnt exist!");
    }

    public Long deleteAuthor(Long ID){
        if (authorRep.existsById(ID)){
            authorRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist");
    }

    public ArrayList<AuthorDTO> getAuthors(){
        List<Author> allAuthors = authorRep.findAll();
        ArrayList<AuthorDTO> DTOAuthorsArray = new ArrayList<>();
        for (Author author : allAuthors){
            AuthorDTO DTOAuthor = new AuthorDTO(author.getAuthorID(), author.getName());
            DTOAuthorsArray.add(DTOAuthor);
        }
        return DTOAuthorsArray;
    }

}
