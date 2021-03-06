package com.openclassroom.client.Controller;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.openclassroom.client.BookServiceBeans.BookAndUser;
import com.openclassroom.client.BookServiceBeans.BooksBean;
import com.openclassroom.client.BookServiceBeans.LoanBean;
import com.openclassroom.client.BookServiceBeans.ReserveBean;
import com.openclassroom.client.BookServiceBeans.UserBean;
import com.openclassroom.client.BookServiceBeans.UserBookModel;
import com.openclassroom.client.Proxy.BookServiceProxy;
import com.openclassroom.client.Proxy.OAuthServerProxy;
import com.openclassroom.client.utilities.CookieUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ClientController {
    @Autowired
    private BookServiceProxy bookServiceProxy;

    @Autowired
    private OAuthServerProxy oAuthServerProxy;

    private static Logger logger = LoggerFactory.getLogger(ClientController.class);

    private boolean isFilteredByAuthor;
    private boolean isFilteredByTitle;
    private boolean isFilteredByIsbn;

    private void setFilterState(String byAuthor, String byTitle, String byIsbn) {
        if (byAuthor.contains("on"))
            isFilteredByAuthor = true;
        else if (byTitle.contains("on"))
            isFilteredByTitle = true;
        else if (byIsbn.contains("on"))
            isFilteredByIsbn = true;
    }

    private void resetFilterState() {
        isFilteredByAuthor = false;
        isFilteredByTitle = false;
        isFilteredByIsbn = false;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String getHome(Model model) {
        return "index";
    }

    @RequestMapping(path = "/books", method = RequestMethod.GET)
    public String getBooks(Model model, @RequestParam(defaultValue = "") String param, @RequestParam(defaultValue = "") String authors, @RequestParam(defaultValue = "") String title, @RequestParam(defaultValue = "") String isbn) {
        setFilterState(authors, title, isbn);
        if (isFilteredByAuthor) {
            model.addAttribute("books", bookServiceProxy.getBookByAuthorsByKeyWord(param));
        } else if (isFilteredByTitle) {
            model.addAttribute("books", bookServiceProxy.getBookByTitleByKeyWord(param));
        } else if (isFilteredByIsbn) {
            model.addAttribute("books", bookServiceProxy.getBookByIsbn(param));
        } else {
            model.addAttribute("books", bookServiceProxy.getBooks());
        }
        resetFilterState();
        return "book";
    }

    @RequestMapping(value = "/login")
    public String Login(HttpServletResponse httpServletResponse, Model model, @ModelAttribute("user") UserBean userBean) {

        ResponseEntity responseEntity = null;
        /*logger.warn("==========================================");
        logger.warn(userBean.toString());
        logger.warn("==========================================");*/
        if (userBean.getPassword() != null || userBean.getLogin() != null) {
            try {
                responseEntity = oAuthServerProxy.login(userBean);
            } catch (Exception e) {
                logger.warn("==========================================");
                logger.warn("Unable to login to OAUTH-SERVER");
                logger.warn("==========================================");
                e.printStackTrace();
                return "login_wrong";
            }
            String token = responseEntity.getHeaders().getFirst("Authorization").replace("Bearer ", "");
            Cookie cookie = CookieUtility.generateCookie(token);
            httpServletResponse.addCookie(cookie);
            return "redirect:/";
        }
        return "login";
    }

    @PostMapping(value = "/logout")
    public String Logout(HttpServletResponse response) {
        CookieUtility.clearCookie(response);
        return "redirect:/";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("user", new UserBean());
        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute("user") UserBean userBean) {
        logger.warn("=============================");
        logger.warn(userBean.toString());
        logger.warn("=============================");
        logger.warn("SENDING NOW TO OAUTHSERVER");
        try {
            oAuthServerProxy.register(userBean);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("=============================");
            logger.warn("=============================");
            return "register_wrong";
        }

        return "index";
    }

    /*@GetMapping(value = "/reserve/{id}")
    public String rentBook(Model model, @PathVariable("id") String id, @CookieValue("access_token") String cookie) {

        UserBookModel ubm = new UserBookModel();
        logger.warn("=============================");
        logger.warn("=============================");
        logger.warn("=============================");
        logger.warn("=============================");
        ubm.setLogin(CookieUtility.getLoginFromJWT(cookie));

        model.addAttribute("user", ubm);
        model.addAttribute("book", new BooksBean());

        return "book";
    }*/

    @RequestMapping(value = "/rent/{id}")
    public String rentBook(@PathVariable("id") String id, @CookieValue("access_token") String cookie) {
        LoanBean loan = new LoanBean();
        UserBookModel ubm = new UserBookModel();
        ResponseEntity<UserBookModel> temp;

        BooksBean book = bookServiceProxy.getBookById(id);

        ubm.setLogin(CookieUtility.getLoginFromJWT(cookie));
        temp = oAuthServerProxy.getAccountByLogin(ubm.getLogin());

        ubm = temp.getBody();
        loan.setBook(book);
        loan.setUser(ubm);
        loan.setBegin(new GregorianCalendar());
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.DAY_OF_MONTH, 14);
        loan.setEnd(end);
        try {
            bookServiceProxy.registerLoan(loan);
        } catch (Exception e) {
            e.printStackTrace();
            return "already_loaned";
        }

        book.setLeft(book.getLeft() - 1);
        BookAndUser entity = new BookAndUser(book, ubm);
        loan = bookServiceProxy.getLoanByBookAndUser(entity).getBody();
        loan.getBook().setLeft(book.getLeft());
        bookServiceProxy.updateLoan(loan);
        bookServiceProxy.updateBook(book);
        return "successful_rent";
    }

    @RequestMapping(value = "/reserve/{id}")
    public String reserveBook(@PathVariable("id") String id, @CookieValue("access_token") String cookie) {
        ReserveBean reserve = new ReserveBean();
        BooksBean book = bookServiceProxy.getBookById(id);

        ResponseEntity<UserBookModel> user = oAuthServerProxy.getAccountByLogin(CookieUtility.getLoginFromJWT(cookie));

        reserve.setBegin(new GregorianCalendar());
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.DAY_OF_MONTH, 14);
        reserve.setEnd(end);

        var a = bookServiceProxy.getReservationByUserId(user.getBody().getId());

        try {
            if (a.getBody().contains(reserve))
                return "already_reserved";
        }catch (Exception e){
            e.printStackTrace();
        }
        user.getBody().getListBooksToAcceptReservations().put(book.getId(), false);
        oAuthServerProxy.updateAccount(user.getBody());
        book.getUserListReservations().add(user.getBody());
        bookServiceProxy.updateBook(book);
        reserve.setBook(book);
        reserve.setUser(user.getBody());
        bookServiceProxy.addReservation(reserve);
        return "successful_reserve";
    }
}
