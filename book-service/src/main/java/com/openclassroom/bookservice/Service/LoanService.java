package com.openclassroom.bookservice.Service;

import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Repository.LoanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService {
    @Autowired
    private LoanRepository repository;

    public void save(Loan loan) {
        repository.save(loan);
    }

    public void delete(Loan loan) {
        repository.delete(loan);
    }

    public Loan findByBook(Books book) {
        return repository.findByBook(book);
    }
    
    public Optional<Loan> findById(String id) {
        return repository.findById(id);
    }

    public Loan findByUser(User user) {
        return repository.findByUser(user.getName());
    }
}