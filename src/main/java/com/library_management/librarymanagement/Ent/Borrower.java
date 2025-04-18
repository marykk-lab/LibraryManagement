package com.library_management.librarymanagement.Ent;

import jakarta.persistence.*;

@Entity
@Table(name="borrow")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long borrower_id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
