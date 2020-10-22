package com.openclassroom.bookservice.Controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Service.BookService;
import com.openclassroom.bookservice.Service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "library/", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private LoanService loanService;

    /*@RequestMapping(value = "book/title/{title}", method = RequestMethod.GET)
    public Optional<Books> getBookByTitle(@PathVariable(value = "title") String title) {
        return bookService.findByTitle(title);
    }*/

    @RequestMapping(value = "book/title/{title}", method = RequestMethod.GET)
    public List<Books> getBookByTitleByKeyWord(@PathVariable(value = "title") String title) {
        return bookService.findByTitleByKeyWord(title);
    }

    @RequestMapping(value = "book/author/{authors}", method = RequestMethod.GET)
    public List<Books> getBookByAuthorsByKeyWord(@PathVariable(value = "authors") String authors) {
        return bookService.findByAuthorsByKeyWord(authors);
    }

    @RequestMapping(value = "book/isbn/{isbn}", method = RequestMethod.GET)
    public Optional<Books> getBookByIsbn(@PathVariable(value = "isbn") String isbn) {
        return bookService.findByIsbn(isbn);
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

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.POST)
    public void deleteBookById(@PathVariable(name = "id") String id){
        bookService.delete(bookService.findById(id).get());
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public ResponseEntity<Books> addBook(@RequestBody Books book) {

        if (book == null) {
            return ResponseEntity.noContent().build();
        }

        if(bookService.findByTitle(book.getTitle()).isEmpty()) {
            bookService.save(book);
        }
            

        URI location = ServletUriComponentsBuilder
                        .fromCurrentContextPath()
                        .path("/{id}")
                        .buildAndExpand(book.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    // Working fine (done by strict User name matching) and can delete only if one of the same entity exists.
    @RequestMapping(value = "book/{bookId}/borrower/delete", method = RequestMethod.POST)
    public void deleteBorrower(@RequestBody User user){
        if (loanService.findByUser(user) != null)
            loanService.delete(loanService.findByUser(user));
    }

    //TO-DO
    @RequestMapping(value = "book/{id}/borrower", method = RequestMethod.GET)
    public User getBookBorrower(User user){
        return loanService.findByUser(user).getUser();
    }

    @RequestMapping(value = "book/update", method = RequestMethod.POST)
    public ResponseEntity<String> updateBook(@RequestBody Books book) {
        bookService.updateBook(book);
        return new ResponseEntity<>("Successfully updated", HttpStatus.OK);
    }

    @PostMapping("/reserve")
    public ResponseEntity<String> reserveBook(@RequestBody Books book) {

        return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
    }
}
