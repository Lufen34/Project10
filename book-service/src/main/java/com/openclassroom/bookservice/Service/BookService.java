package com.openclassroom.bookservice.Service;

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

    public Optional<Books> findByPublisher(String publisher) {
        return repository.findByPublisher(publisher);
    }

    public Optional<Books> findByAuthor(String author) {
        return repository.findByAuthor(author);
    }

    public Optional<Books> findById(String id) {
        return repository.findById(id);
    }

    public void save(Books book) {
        repository.save(book);
    }

    public void delete(Books book) {
        repository.delete(book);
    }
}