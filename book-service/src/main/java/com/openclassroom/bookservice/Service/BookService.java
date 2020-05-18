package com.openclassroom.bookservice.Service;

import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    public Optional<Books> findByTitle(String title) {
        return repository.findByTitle(title);
    }

    public Optional<Books> findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }

    public Optional<Books> findByPublisher(String publisher) {
        return repository.findByPublisher(publisher);
    }

    public List<Books> findByAuthorsByKeyWord(String author) {
        return repository.findByAuthorsByKeyWord(author);
    }

    public List<Books> findByTitleByKeyWord(String title) {
        return repository.findByTitleByKeyWord(title);
    }

    public Optional<Books> findById(String id) {
        return repository.findById(id);
    }

    public List<Books> findAllBooks(){
        return repository.findAll();
    }

    public List<Books> inStock() {
        return repository.hasInStock();
    }

    public void save(Books book) {
        repository.save(book);
    }

    public void delete(Books book) {
        repository.delete(book);
    }
}