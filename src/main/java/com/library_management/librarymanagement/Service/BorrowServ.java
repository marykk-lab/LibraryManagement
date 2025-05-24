package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.BorrowDTO;
import com.library_management.librarymanagement.DTOs.BorrowSaveDTO;
import com.library_management.librarymanagement.Entities.Book;
import com.library_management.librarymanagement.Entities.Borrow;
import com.library_management.librarymanagement.Entities.User;
import com.library_management.librarymanagement.Repositories.BookRep;
import com.library_management.librarymanagement.Repositories.BorrowRep;
import com.library_management.librarymanagement.Repositories.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BorrowServ {
    @Autowired
    private UserRep userRep;

    @Autowired
    private BorrowRep borrowRep;

    @Autowired
    private BookRep bookRep;

    public Long addBorrow(BorrowSaveDTO borrowSaveDTO){
        if (borrowSaveDTO!=null){
            LocalDate localDate = LocalDate.now();
            Book book = bookRep.getReferenceById(borrowSaveDTO.getBookID());
            User user = userRep.getReferenceById(borrowSaveDTO.getUserID());
            Borrow borrow = new Borrow(localDate, localDate.plusDays(5), book, user);
            book.addBorrow(borrow);
            borrowRep.save(borrow);
            return borrowSaveDTO.getBookID();
        }
        throw new IllegalArgumentException("Form is empty!");
    }

    public ArrayList<BorrowDTO> getBorrows(){
        List<Borrow> allBorrows = borrowRep.findAll();
        ArrayList<BorrowDTO> DTOBorrowsArray = new ArrayList<>();
        for (Borrow borrow : allBorrows){
            BorrowDTO borrowDTO = new BorrowDTO(borrow.getBorrowID(), borrow.getBorrowingDate(), borrow.getReturnDate(), borrow.getBook().getBookID(), borrow.getUser().getUserID());
            DTOBorrowsArray.add(borrowDTO);
        }
        return DTOBorrowsArray;
    }

    public Long deleteBorrowByID(Long ID){
        if (borrowRep.existsById(ID)){
            borrowRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public BorrowDTO getBorrowByID(Long ID){
        if (borrowRep.existsById(ID)) {
            Borrow borrow = borrowRep.getReferenceById(ID);
            BorrowDTO borrowDTO = new BorrowDTO(borrow.getBorrowID(), borrow.getBorrowingDate(),
                    borrow.getReturnDate(), borrow.getBook().getBookID(), borrow.getUser().getUserID());
            return borrowDTO;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }
}
