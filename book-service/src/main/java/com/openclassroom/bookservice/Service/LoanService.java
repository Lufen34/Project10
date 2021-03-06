package com.openclassroom.bookservice.Service;

import java.util.List;
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
    public Loan findByBookId(String id) {
        return repository.findByBookId(id);
    }

    public Optional<Loan> findById(String id) {
        return repository.findById(id);
    }

    public Loan findByUser(User user) {
        return repository.findByUser(user.getName());
    }

    public List<Loan> findAllLoanFromUser(String user) {
        return repository.findAllLoanFromUserByEmail(user);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public List<Loan> findAll() {
        return repository.findAll();
    }

    public Loan findByBookAndUser(Books book, User user){
        return repository.findByBookIdAndAndUserId(book.getId(), user.getId());
    }
}
