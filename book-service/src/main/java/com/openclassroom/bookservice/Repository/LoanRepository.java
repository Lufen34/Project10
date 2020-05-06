package com.openclassroom.bookservice.Repository;

import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LoanRepository extends MongoRepository<Loan, String> {
    public Optional<Loan> findById(String id);
    public Loan findByBook(Books book);
    // '' needed for nested fields
    @Query("{'user.name': ?0}")
    public Loan findByUser(String username);
}