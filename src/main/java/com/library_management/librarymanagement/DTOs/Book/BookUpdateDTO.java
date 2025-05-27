package com.library_management.librarymanagement.DTOs.Book;

public class BookUpdateDTO {
    private Long bookID;
    private String title;
    private Long authorID;
    private String description;
    private Integer quantity;
    private String imageUrl;

    public BookUpdateDTO(Long bookID, String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.bookID = bookID;
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public BookUpdateDTO(String title, Long authorID, String description, Integer quantity, String imageUrl) {
        this.title = title;
        this.authorID = authorID;
        this.description = description;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
    }

    public BookUpdateDTO() {
    }

    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Long authorID) {
        this.authorID = authorID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "BookUpdateDTO{" +
                "bookID=" + bookID +
                ", title='" + title + '\'' +
                ", authorID=" + authorID +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
