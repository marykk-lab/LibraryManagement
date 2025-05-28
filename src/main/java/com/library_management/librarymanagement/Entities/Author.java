package com.library_management.librarymanagement.Entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorID;

    @Column(name = "author_name", length = 40)
    private String name;

    private String wikiUrl;
    private String imageUrl;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(Long authorID, String name, String wikiUrl, String imageUrl) {
        this.authorID = authorID;
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    public Author(String name, String wikiUrl, String imageUrl) {
        this.name = name;
        this.wikiUrl = wikiUrl;
        this.imageUrl = imageUrl;
    }

    public boolean addBook(Book book){
        if (book != null){
            books.add(book);
            return true;
        }
        return false;
    }


    public Author() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }


    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
