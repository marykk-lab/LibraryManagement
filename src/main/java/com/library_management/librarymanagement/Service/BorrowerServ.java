package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.BookDTO;
import com.library_management.librarymanagement.DTOs.BorrowerDTO;
import com.library_management.librarymanagement.DTOs.BorrowerSaveDTO;
import com.library_management.librarymanagement.DTOs.BorrowerUpdateDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.Borrower;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.BookRep;
import com.library_management.librarymanagement.Repositories.BorrowerRep;
import com.library_management.librarymanagement.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowerServ {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BorrowerRep borrowerRep;

    @Autowired
    private BookRep bookRep;

    public Long addBorrow(BorrowerSaveDTO borrowerSaveDTO){
        if (borrowerSaveDTO!=null){
            LocalDate localDate = LocalDate.now();
            Book book = bookRep.getReferenceById(borrowerSaveDTO.getBookID());
            User user = userRepo.getReferenceById(borrowerSaveDTO.getUserID());
            Borrower borrower = new Borrower(localDate, localDate.plusDays(5), book, user);
            borrowerRep.save(borrower);
            return borrowerSaveDTO.getBookID();
        }
        throw new IllegalArgumentException("Form is empty!");
    }

    public ArrayList<BorrowerDTO> getBorrowers(){
        List<Borrower> allBorrows = borrowerRep.findAll();
        ArrayList<BorrowerDTO> DTOBorrowsArray = new ArrayList<>();
        for (Borrower borrower : allBorrows){
            BorrowerDTO borrowerDTO = new BorrowerDTO(borrower.getBorrowerID(), borrower.getBorrowingDate(), borrower.getReturnDate(), borrower.getBook(), borrower.getUser());
            DTOBorrowsArray.add(borrowerDTO);
        }
        return DTOBorrowsArray;
    }

    public Long deleteBorrowerByID(Long ID){
        if (borrowerRep.existsById(ID)){
            borrowerRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }
}
