package com.openclassroom.client.Proxy;

import java.util.List;

import javax.validation.Valid;

import com.openclassroom.client.BookServiceBeans.BookAndUser;
import com.openclassroom.client.BookServiceBeans.BooksBean;
import com.openclassroom.client.BookServiceBeans.LoanBean;
import com.openclassroom.client.BookServiceBeans.ReserveBean;
import com.openclassroom.client.BookServiceBeans.UserBean;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "GATEWAY-SERVER", contextId = "BOOK-SERVICE")
@RibbonClient(name = "BOOK-SERVICE")
@RequestMapping(value = "BOOK-SERVICE/library")
public interface BookServiceProxy {

    @RequestMapping(value = "book/title/{title}", method = RequestMethod.GET)
    List<BooksBean> getBookByTitleByKeyWord(@PathVariable(value = "title") String title);

    @RequestMapping(value = "book/author/{author}", method = RequestMethod.GET)
    List<BooksBean> getBookByAuthorsByKeyWord(@PathVariable(value = "author") String author);

    @RequestMapping(value = "book/id/{id}", method = RequestMethod.GET)
    BooksBean getBookById(@PathVariable(value = "id") String id);

    @RequestMapping(value = "book/isbn/{isbn}", method = RequestMethod.GET)
    BooksBean getBookByIsbn(@PathVariable(value = "isbn") String isbn);

    @RequestMapping(value = "book/stock", method = RequestMethod.GET)
    List<BooksBean> getBooksInStock();
    
    @RequestMapping(value = "book", method = RequestMethod.GET)
    List<BooksBean> getBooks();

    @RequestMapping(value = "book/delete/{id}", method = RequestMethod.POST)
    void deleteBookById(@PathVariable(name = "id") String id);

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    ResponseEntity<BooksBean> addBook(@RequestBody BooksBean book);

    @RequestMapping(value = "book/{bookId}/borrower/add", method = RequestMethod.POST)
    ResponseEntity<Bean> addBorrower(@PathVariable(name = "bookId") String bookId, @RequestBody UserBean user);

    @RequestMapping(value = "book/{bookId}/borrower/delete", method = RequestMethod.POST)
    void deleteBorrower(@RequestBody UserBean user);

    @RequestMapping(value = "book/{id}/borrower", method = RequestMethod.GET)
    UserBean getBookBorrower(UserBean user);

    @PostMapping(value = "/rent")
    ResponseEntity<String> registerLoan(@RequestBody LoanBean loan);

    @RequestMapping(value = "/loans", method = RequestMethod.POST)
    ResponseEntity<List<LoanBean>> getLoans(@RequestBody String email);

    @PostMapping("/loan/{id}")
    ResponseEntity<LoanBean> getLoan(@PathVariable("id") String id);

    @PostMapping("/loan/update/")
    ResponseEntity<String> updateLoan(@RequestBody LoanBean loan);

    @PostMapping("/book/update/")
    ResponseEntity<String> updateBook(@RequestBody BooksBean book);

    @PostMapping("book/reservation/add")
    void addReservation(@RequestBody ReserveBean reserve);

    @GetMapping("book/reservations/{UserId}")
    ResponseEntity<List<ReserveBean>> getReservationByUserId(@PathVariable(value = "UserId") String id);

    @PostMapping("/loan/delete/")
    ResponseEntity<String> deleteLoan(@RequestBody LoanBean loan);

    @PostMapping("book/reservation/delete")
    ResponseEntity<String> deleteReservation(@RequestBody ReserveBean reserve);

    @PostMapping("/book/reservation/{id}")
    ResponseEntity<ReserveBean> getReservation(@PathVariable("id") String id);

    @PostMapping("/loan/book/")
    ResponseEntity<LoanBean> getLoanByBookAndUser(@Valid @RequestBody BookAndUser entity);

    @PostMapping("book/reservation/")
    ResponseEntity<ReserveBean> getReservationByBookAndUser(@RequestBody BookAndUser bookAndUser);

    @PostMapping("/reserve/update/")
    ResponseEntity<String> updateReservation(@RequestBody ReserveBean reserve);
}
