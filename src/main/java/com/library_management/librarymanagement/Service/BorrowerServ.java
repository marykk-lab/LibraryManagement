package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.BorrowerSaveDTO;
import com.library_management.librarymanagement.Entities.Borrower;
import com.library_management.librarymanagement.Repositories.AuthorRep;
import com.library_management.librarymanagement.Repositories.BookRep;
import com.library_management.librarymanagement.Repositories.BorrowerRep;
import com.library_management.librarymanagement.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BorrowerServ {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BorrowerRep borrowerRep;

    @Autowired
    private BookRep bookRep;

    public String addBorrow(BorrowerSaveDTO borrowerSaveDTO){
        if (borrowerSaveDTO!=null){
            Borrower borrower = new Borrower();
        }
    }
}
