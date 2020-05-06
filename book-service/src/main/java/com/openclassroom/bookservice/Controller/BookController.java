package com.openclassroom.bookservice.Controller;

import java.util.List;
import java.util.Optional;

import com.openclassroom.bookservice.Model.Books;
import com.openclassroom.bookservice.Model.Loan;
import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Service.BookService;
import com.openclassroom.bookservice.Service.UserService;
import com.openclassroom.bookservice.Service.LoanService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping(value = "library/")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoanService loanService;

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

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.POST)
    public void deleteBookById(@PathVariable(name = "id") String id){
        bookService.delete(bookService.findById(id).get());
    }

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public void addBook(@RequestBody Books book){
        bookService.save(book);
    }

    /**
     * TO-DO : Check if user doesn't already exists in order to avoid duplicates inside the db
     * @param bookId
     * @param user
     */
    @RequestMapping(value = "book/{bookId}/borrower/add", method = RequestMethod.POST)
    public void addBorrower(@PathVariable(name="bookId") String bookId, @RequestBody User user){
        Loan loan = new Loan(bookService.findById(bookId).get(), user);
        userService.save(user);
        loanService.save(loan);
    }
    // Working fine (done by strict User name matching)
    @RequestMapping(value = "book/{bookId}/borrower/delete", method = RequestMethod.POST)
    public void deleteBorrower(@PathVariable(name="bookId") String bookId, @RequestBody User user){
        loanService.delete(loanService.findByUser(user));
    }
    //TO-DO
    @RequestMapping(value = "book/{id}/borrower", method = RequestMethod.GET)
    public User getBookBorrower(User user){
        return loanService.findByUser(user).getUser();
    }
}