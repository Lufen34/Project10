package com.openclassroom.bookservice.Repository;

import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Books, String> {
    // the "?0" replace is replaced by the first parameter of the method.
    //@Query("{title: ?0}")
    public Optional<Books> findByPublisher(String title);
    public Optional<Books> findByTitle(String title);
    public Optional<Books> findById(String title);
    public Optional<Books> findByAuthor(String title);
}