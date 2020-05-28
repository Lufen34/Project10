package com.openclassroom.client.Proxy;

import java.util.List;
import java.util.Optional;

import com.openclassroom.client.BookServiceBeans.BooksBean;
import com.openclassroom.client.BookServiceBeans.UserBean;
import com.openclassroom.client.BookServiceBeans.LoanBean;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "GATEWAY-SERVER")
@RibbonClient(name = "BOOK-SERVICE")
@RequestMapping(value = "BOOK-SERVICE/library")
public interface BookServiceProxy {

    @RequestMapping(value = "book/title/{title}", method = RequestMethod.GET)
    List<BooksBean> getBookByTitleByKeyWord(@PathVariable(value = "title") String title);
    
    @RequestMapping(value = "book/publisher/{publisher}", method = RequestMethod.GET)
    BooksBean getBookByPublisher(@PathVariable(value = "publisher") String publisher);

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

}