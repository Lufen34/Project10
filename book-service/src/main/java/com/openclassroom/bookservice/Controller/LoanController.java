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
import java.util.Optional;

@RestController
@RequestMapping(value = "library/", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/rent")
    public ResponseEntity<String> registerLoan(@RequestBody Loan loan) {
        if (loanService.findByBookId(loan.getBook().getId()) == null)
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

    @PostMapping("/loan/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable("id") String id){
        Optional<Loan> loan = loanService.findById(id);
        return new ResponseEntity<Loan>(loan.get(), HttpStatus.OK);
    }

    @PostMapping("/loan/update/")
    public ResponseEntity<String> updateLoan(@RequestBody Loan loan){
        loanService.deleteById(loan.getId());
        loanService.save(loan);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @RequestMapping(value = "/loans/all", method = RequestMethod.POST)
    public List<Loan> getALlLoans(){
        return loanService.findAll();
    }
}
