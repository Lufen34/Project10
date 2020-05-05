package com.openclassroom.bookservice.Controller;

import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
@RequestMapping(value = "library/")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "book/title/{title}", method = RequestMethod.GET)
    public Optional<Books> getBookByTitle(@PathVariable(value = "title") String title) {
        return bookService.findByTitle(title);
    }
    
    @RequestMapping(value = "book/publisher/{publisher}", method = RequestMethod.GET)
    public Optional<Books> getBookByPublisher(@PathVariable(value = "publisher") String publisher) {
        return bookService.findByPublisher(publisher);
    }

    @RequestMapping(value = "book/author/{author}", method = RequestMethod.GET)
    public Optional<Books> getBookByAuthor(@PathVariable(value = "author") String author) {
        return bookService.findByAuthor(author);
    }

    @RequestMapping(value = "book/id/{id}", method = RequestMethod.GET)
    public Optional<Books> getBookById(@PathVariable(value = "id") String id) {
        return bookService.findById(id);
    }

    @RequestMapping(value = "book/stock", method = RequestMethod.GET)
    public List<Books> getBooksInStock() {
        return bookService.inStock();
    }
    
    @RequestMapping(value = "book", method = RequestMethod.GET)
    public List<Books> getBooks() {
        return bookService.findAllBooks();
    }

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.GET)
    public void deleteBookById(@PathVariable(name = "id") String id){
        bookService.delete(bookService.findById(id).get());
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public void addBook(@RequestBody Books book){
        bookService.save(book);
    }
}