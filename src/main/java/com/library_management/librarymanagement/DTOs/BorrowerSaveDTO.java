package com.library_management.librarymanagement.DTOs;

public class BorrowerSaveDTO {
    private Long bookID;
    private Long userID;

    public BorrowerSaveDTO(Long bookID, Long userID) {
        this.bookID = bookID;
        this.userID = userID;
    }

    public BorrowerSaveDTO() {
    }


    public Long getBookID() {
        return bookID;
    }

    public void setBookID(Long authorID) {
        this.bookID = authorID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
