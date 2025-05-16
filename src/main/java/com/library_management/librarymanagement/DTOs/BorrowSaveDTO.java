package com.library_management.librarymanagement.DTOs;

public class BorrowSaveDTO {
    private Long bookID;
    private Long userID;

    public BorrowSaveDTO(Long bookID, Long userID) {
        this.bookID = bookID;
        this.userID = userID;
    }

    public BorrowSaveDTO() {
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
