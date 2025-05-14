package com.library_management.librarymanagement.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.library_management.librarymanagement.DTOs.BorrowerDTO;
import com.library_management.librarymanagement.DTOs.BorrowerSaveDTO;
import com.library_management.librarymanagement.DTOs.BorrowerUpdateDTO;
import com.library_management.librarymanagement.Service.BorrowerServ;

import java.util.List;

@RestController
@RequestMapping("api/borrower")
public class BorrowerRestContr {
    @Autowired
    private BorrowerServ borrowerServ;

    @PostMapping(path = "/add_borrow")
    public Long addAuthor(@RequestBody BorrowerSaveDTO borrowerSaveDTO){
        return borrowerServ.addBorrow(borrowerSaveDTO);
    }

    @GetMapping(path = "/get_all_borrows")
    public List<BorrowerDTO> getBorrows(){
        return borrowerServ.getBorrowers();
    }

    @GetMapping(path = "/get_borrow_by_id/{id}")
    public BorrowerDTO getBorrowByID(@PathVariable(value = "id")Long id){
        return borrowerServ.getBorrowerByID(id);
    }

    @DeleteMapping(path = "/delete_borrow_by_id/{id}")
    public Long deleteBorrowById(@PathVariable(value = "id")Long id){
        return borrowerServ.deleteBorrowerByID(id);
    }
}
