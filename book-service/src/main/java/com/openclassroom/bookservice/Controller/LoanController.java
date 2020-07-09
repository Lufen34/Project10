package com.openclassroom.bookservice.Controller;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/loans", method = RequestMethod.POST)
    public ResponseEntity<List<Loan>> getLoans(@RequestBody String email) {
        List<Loan> loans = loanService.findAllLoanFromUser(email);
        return new ResponseEntity<List<Loan>>(loans, HttpStatus.OK);
    }
}
