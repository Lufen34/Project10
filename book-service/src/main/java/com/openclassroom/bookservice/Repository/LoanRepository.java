package com.openclassroom.bookservice.Repository;

import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface LoanRepository extends MongoRepository<Loan, String> {
    public Optional<Loan> findById(String id);
    public void deleteById(String id);
    public Loan findByBook(Books book);
    public Loan findByBookId(String id);
    Loan findByBookIdAndAndUserId(String bookId, String userId);
    // '' needed for nested fields
    @Query("{'user.name': ?0}")
    public Loan findByUser(String username);
    @Query("{'user.email' : ?0}")
    public List<Loan> findAllLoanFromUserByEmail(String name);
}
