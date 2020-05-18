package com.openclassroom.bookservice.Repository;

import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Books, String> {
    // the "?0" replace is replaced by the first parameter of the method.
    //@Query("{title: ?0}")
    public Optional<Books> findByPublisher(String publisher);
    public Optional<Books> findByTitle(String title);
    public Optional<Books> findById(String id);
    public Optional<Books> findByIsbn(String isbn);
    //@Query("{ authors: { $in: [ /.*?0.*/ ] } }")
    //@Query("{ authors: /.*?0.*/ } ")
   //public List<Books> findByAuthorsContaining(String authors);
    @Query("{ 'title': { '$regex' : ?0, $options : 'i'}}")
    public List<Books> findByTitleByKeyWord(String title);
    @Query("{ 'authors': { '$regex' : ?0, $options : 'i'}}")
    public List<Books> findByAuthorsByKeyWord(String authors);
    @Query("{stock: {$gt: 0}}")
    public List<Books> hasInStock();
}