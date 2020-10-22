package com.openclassroom.bookservice.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.openclassroom.bookservice.Model.BookAndUser;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Service.LoanService;

import org.bson.internal.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        List<Loan> loans = loanService.findAllLoanFromUser(arr.toString());
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

    @PostMapping("/loan/delete/")
    public ResponseEntity<String> deleteLoan(@RequestBody Loan loan) {
        loanService.delete(loan);
        return new ResponseEntity<String>("Loan successfully deleted.", HttpStatus.OK);
    }

    @PostMapping("/loan/book/")
    public ResponseEntity<Loan> getLoanByBookAndUser(@Valid @RequestBody BookAndUser entity) {
        return new ResponseEntity<Loan>(loanService.findByBookAndUser(entity.getBook(), entity.getUser()), HttpStatus.OK);
    }
}
