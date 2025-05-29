
package com.library_management.librarymanagement.Controllers.REST;

import com.library_management.librarymanagement.DTOs.Borrow.BorrowDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowSaveDTO;
import com.library_management.librarymanagement.DTOs.Borrow.BorrowUpdateDTO;
import com.library_management.librarymanagement.Service.BorrowServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing book borrowing operations.
 */
@RestController
@RequestMapping(path = "/api/borrow")
public class BorrowRestContr {

    @Autowired
    private BorrowServ borrowServ;

    /**
     * Creates a new borrow record.
     *
     * @param borrowSaveDTO DTO containing borrow details
     * @return ID of the created borrow record
     */
    @PostMapping
    public Long addBorrow(@RequestBody BorrowSaveDTO borrowSaveDTO){
        return borrowServ.addBorrow(borrowSaveDTO);
    }

    /**
     * Retrieves all borrow records.
     *
     * @return list of all borrows
     */
    @GetMapping(path = "/admin")
    public List<BorrowDTO> getBorrows(){
        return borrowServ.getBorrows();
    }

    /**
     * Retrieves a specific borrow record.
     *
     * @param id borrow ID
     * @return borrow details
     */
    @GetMapping(path = "/{id}")
    public BorrowDTO getBorrowByID(@PathVariable Long id){
        return borrowServ.getBorrowByID(id);
    }

    /**
     * Deletes a borrow record.
     *
     * @param id borrow ID to delete
     * @return ID of deleted borrow
     */
    @DeleteMapping(path = "/admin/{id}")
    public Long deleteBorrowById(@PathVariable Long id){
        return borrowServ.deleteBorrowByID(id);
    }

    /**
     * Updates a borrow record.
     *
     * @param borrowUpdateDTO DTO containing updated borrow details
     * @return confirmation message
     */
    @PutMapping(path = "/admin")
    public String updateBorrow(@RequestBody BorrowUpdateDTO borrowUpdateDTO){
        return borrowServ.updateBorrow(borrowUpdateDTO);
    }
}