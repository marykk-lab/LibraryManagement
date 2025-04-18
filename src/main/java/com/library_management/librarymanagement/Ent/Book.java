package com.library_management.librarymanagement.Ent;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookID;

    @Column(name = "title", length = 50)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Borrower> borrowers;

}
