package com.library_management.librarymanagement.Service;

import com.library_management.librarymanagement.DTOs.Borrow.BorrowDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowUpdateDTO;
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
            Book book = bookRep.getReferenceById(borrowSaveDTO.getBookID());
            if (book.getQuantity()>0){
                LocalDate localDate = LocalDate.now();
                User user = userRep.getReferenceById(borrowSaveDTO.getUserID());
                Borrow borrow = new Borrow(localDate, localDate.plusDays(5), book, user);
                book.addBorrow(borrow);
                book.minusBook();
                user.addBorrow(borrow);
                borrowRep.save(borrow);
                return borrowSaveDTO.getBookID();
            }
            throw new ArithmeticException("There is no book left in the system");
        }
        throw new IllegalArgumentException("Form is empty!");
    }

    public ArrayList<BorrowDTO> getBorrows(){
        List<Borrow> allBorrows = borrowRep.findAll();
        ArrayList<BorrowDTO> DTOBorrowsArray = new ArrayList<>();
        for (Borrow borrow : allBorrows){
            String title = borrowRep.getReferenceById(borrow.getBorrowID()).getBook().getTitle();
            String username = borrowRep.getReferenceById(borrow.getBorrowID()).getUser().getUsername();
            BorrowDTO borrowDTO = new BorrowDTO(borrow.getBorrowID(), borrow.getBorrowingDate(),
                                                borrow.getReturnDate(), borrow.getBook().getBookID(),
                                                borrow.getUser().getUserID(), title, username);
            DTOBorrowsArray.add(borrowDTO);
        }
        return DTOBorrowsArray;
    }

    public Long deleteBorrowByID(Long ID){
        if (borrowRep.existsById(ID)){
            Borrow borrow = borrowRep.getReferenceById(ID);
            Book book = bookRep.getReferenceById(borrow.getBook().getBookID());
            book.plusBook();
            borrowRep.deleteById(ID);
            return ID;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public BorrowDTO getBorrowByID(Long ID){
        if (borrowRep.existsById(ID)) {
            Borrow borrow = borrowRep.getReferenceById(ID);
            String title = borrow.getBook().getTitle();
            String username = borrow.getUser().getUsername();
            BorrowDTO borrowDTO = new BorrowDTO(borrow.getBorrowID(), borrow.getBorrowingDate(),
                                                borrow.getReturnDate(), borrow.getBook().getBookID(),
                                                borrow.getUser().getUserID(), title, username);
            return borrowDTO;
        }
        throw new IllegalArgumentException("This ID doesnt exist!");
    }

    public String updateBorrow(BorrowUpdateDTO borrowUpdateDTO){
        if(borrowRep.existsById(borrowUpdateDTO.getBorrowID())){
            Borrow borrow = borrowRep.getReferenceById(borrowUpdateDTO.getBorrowID());
            borrow.setBook(bookRep.getReferenceById(borrowUpdateDTO.getBookID()));
            borrow.setUser(userRep.getReferenceById(borrowUpdateDTO.getUserID()));
            borrow.setReturnDate(borrowUpdateDTO.getReturnDate());
            borrow.setBorrowingDate(borrowUpdateDTO.getBorrowingDate());
            borrowRep.save(borrow);
            return "Borrow was updated!";
        }
        throw new IllegalArgumentException("This ID doesnt exist!");

    }
}
