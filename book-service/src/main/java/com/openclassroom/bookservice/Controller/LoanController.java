package com.openclassroom.bookservice.Controller;

import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "library/", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/reserve")
    public ResponseEntity<String> registerLoan(@RequestBody Loan loan) {
        if (loanService.findByBook(loan.getBook()) == null)
            loanService.save(loan);
        else
            return new ResponseEntity<>("You have already loaned this book", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
    }
}
