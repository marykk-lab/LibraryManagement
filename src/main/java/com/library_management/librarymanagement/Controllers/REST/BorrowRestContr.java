package com.library_management.librarymanagement.Controllers.REST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowUpdateDTO;
import com.library_management.librarymanagement.Service.BorrowServ;

import java.util.List;

@RestController
@RequestMapping("api/borrow")
public class BorrowRestContr {
    @Autowired
    private BorrowServ borrowServ;

    @PostMapping
    public Long addBorrow(@RequestBody BorrowSaveDTO borrowSaveDTO){
        return borrowServ.addBorrow(borrowSaveDTO);
    }

    @GetMapping(path = "/admin")
    public List<BorrowDTO> getBorrows(){
        return borrowServ.getBorrows();
    }

    @GetMapping(path = "/{id}")
    public BorrowDTO getBorrowByID(@PathVariable Long id){
        return borrowServ.getBorrowByID(id);
    }

    @DeleteMapping(path = "/admin/{id}")
    public Long deleteBorrowById(@PathVariable Long id){
        return borrowServ.deleteBorrowByID(id);
    }

    @PutMapping(path = "/admin")
    public String updateBorrow(@RequestBody BorrowUpdateDTO borrowUpdateDTO){
        return borrowServ.updateBorrow(borrowUpdateDTO);
    }
}
