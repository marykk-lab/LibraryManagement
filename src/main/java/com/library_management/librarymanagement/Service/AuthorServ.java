package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Author.AuthorDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorSaveDTO;
import com.library_management.librarymanagement.DTOs.Author.AuthorUpdateDTO;
import com.library_management.librarymanagement.DTOs.Book.BookDTO;
import com.library_management.librarymanagement.Entities.Author;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthorServ{
    @Autowired
    private AuthorRep authorRep;

    public String addAuthor(AuthorSaveDTO authorSaveDTO) {
         String name = authorSaveDTO.getName();
         if (!name.isEmpty() && !name.isBlank()){
             Author newAuthor = new Author(name, authorSaveDTO.getWikiUrl(), authorSaveDTO.getImageUrl());
             authorRep.save(newAuthor);
             return "Author was added - " + name;
         }
         throw new IllegalArgumentException("Author name is required!");
    }

    public String updateAuthor(AuthorUpdateDTO authorUpdateDTO) {
        if (authorRep.existsById(authorUpdateDTO.getAuthorID())) {
            Author author = authorRep.getReferenceById(authorUpdateDTO.getAuthorID());
            author.setName(authorUpdateDTO.getName());
            author.setWikiUrl(authorUpdateDTO.getWikiUrl());
            author.setImageUrl(authorUpdateDTO.getImageUrl());
            authorRep.save(author);
            return "Author was updated - " + author.getName();
        }
        throw new IllegalArgumentException("Author ID doesnt exist!");
    }

    public Long deleteAuthorById(Long ID){
        if (authorRep.existsById(ID)){
            authorRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public ArrayList<AuthorDTO> getAuthors(){
        List<Author> allAuthors = authorRep.findAll();
        ArrayList<AuthorDTO> DTOAuthorsArray = new ArrayList<>();
        for (Author author : allAuthors){
            AuthorDTO DTOAuthor = new AuthorDTO(author.getAuthorID(), author.getName(), author.getWikiUrl(), author.getImageUrl());
            DTOAuthorsArray.add(DTOAuthor);
        }
        return DTOAuthorsArray;
    }

    public AuthorDTO getAuthorByID(Long ID){
        if(authorRep.existsById(ID)){
            Author author = authorRep.getReferenceById(ID);
            AuthorDTO authorDTO = new AuthorDTO(author.getAuthorID(), author.getName(), author.getWikiUrl(), author.getImageUrl());
            return authorDTO;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public String deleteAuthorByName(String name){
        List<Author> allAuthors = authorRep.findAll();
        if (!name.isBlank()&&!name.isEmpty()){
            for (Author author : allAuthors){
                if (author.getName().equals(name)){
                    authorRep.deleteById(author.getAuthorID());
                    return "Book was deleted - " + name;
                }
            }
        }
        throw new IllegalArgumentException("This name doesnt exist!");
    }


    public Set<Book> getBooksById(Long ID){
        if (authorRep.existsById(ID)){
            Author author = authorRep.getReferenceById(ID);
            return author.getBooks();
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public Author getAuthorEntity(Long ID){
        if (ID!=null && authorRep.existsById(ID)){
            Author author = authorRep.getReferenceById(ID);
            return author;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public List<AuthorDTO> searchAuthorsByName(String name) {
        List<Author> foundAuthors = authorRep.findByNameIgnoreCaseContaining(name);
        List<AuthorDTO> result = new ArrayList<>();
        for (Author author : foundAuthors) {
            result.add(new AuthorDTO(
                    author.getAuthorID(), author.getName(), author.getWikiUrl(), author.getImageUrl()
            ));
        }
        return result;
    }
}
