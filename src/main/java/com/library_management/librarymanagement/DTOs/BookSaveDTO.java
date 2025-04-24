package com.library_management.librarymanagement.DTOs;

public class BookSaveDTO {
    private String title;
    private Long authorID;
    private Long publisherID;

    public BookSaveDTO(String title, Long authorID, Long publisherID) {
        this.title = title;
        this.authorID = authorID;
        this.publisherID = publisherID;
    }

    public BookSaveDTO() {
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

    public Long getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Long publisherID) {
        this.publisherID = publisherID;
    }

    @Override
    public String toString() {
        return "BookSaveDTO{" +
                "title='" + title + '\'' +
                ", authorID=" + authorID +
                ", publisherID=" + publisherID +
                '}';
    }
}
