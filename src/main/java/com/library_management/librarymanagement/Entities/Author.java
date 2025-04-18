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

    @OneToMany(mappedBy = "author")
    private Set<Book> books;

    public Author(String name, Long authorID) {
        this.name = name;
        this.authorID = authorID;
    }

    public Author(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", authorID=" + authorID +
                '}';
    }
}
