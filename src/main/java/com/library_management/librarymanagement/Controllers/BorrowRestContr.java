package com.library_management.librarymanagement.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.library_management.librarymanagement.DTOs.BorrowDTO;
import com.library_management.librarymanagement.DTOs.BorrowSaveDTO;
import com.library_management.librarymanagement.DTOs.BorrowUpdateDTO;
import com.library_management.librarymanagement.Service.BorrowServ;

import java.util.List;

@RestController
@RequestMapping("api/borrow")
public class BorrowRestContr {
    @Autowired
    private BorrowServ borrowServ;

    @PostMapping(path = "/add_borrow")
    public Long addAuthor(@RequestBody BorrowSaveDTO borrowSaveDTO){
        return borrowServ.addBorrow(borrowSaveDTO);
    }

    @GetMapping(path = "/get_all_borrows")
    public List<BorrowDTO> getBorrows(){
        return borrowServ.getBorrows();
    }

    @GetMapping(path = "/get_borrow_by_id/{id}")
    public BorrowDTO getBorrowByID(@PathVariable(value = "id")Long id){
        return borrowServ.getBorrowByID(id);
    }

    @DeleteMapping(path = "/delete_borrow_by_id/{id}")
    public Long deleteBorrowById(@PathVariable(value = "id")Long id){
        return borrowServ.deleteBorrowByID(id);
    }
}
