package com.openclassroom.client.Controller;

import com.openclassroom.client.BookServiceBeans.*;
import com.openclassroom.client.Proxy.BatchServiceProxy;
import com.openclassroom.client.Proxy.BookServiceProxy;
import com.openclassroom.client.Proxy.OAuthServerProxy;
import com.openclassroom.client.utilities.CookieUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Controller
public class MySpace {

    @Autowired
    BookServiceProxy bookServiceProxy;

    @Autowired
    private OAuthServerProxy oAuthServerProxy;

    @Autowired
    private BatchServiceProxy batchServiceProxy;

    private Calendar today = Calendar.getInstance();

    private static Logger logger = LoggerFactory.getLogger(MySpace.class);

    @GetMapping("/my_space")
    public String getSpace(Model model, @CookieValue("access_token") Cookie cookie){
        String login = CookieUtility.getLoginFromJWT(cookie.getValue());

        today.setTime(new Date());

        ResponseEntity<UserBookModel> res = oAuthServerProxy.getAccountByLogin(login);

        UserBookModel user = res.getBody();

        logger.warn("=====================================");
        logger.warn(user.getEmail());
        logger.warn("=====================================");

        ResponseEntity<List<LoanBean>> response = bookServiceProxy.getLoans(user.getEmail());
        List<LoanBean> loans = response.getBody();

        ResponseEntity<List<ReserveBean>> resp = bookServiceProxy.getReservationByUserId(user.getId());
        List<ReserveBean> reservations = resp.getBody();


        for (ReserveBean reservation : reservations){
            // If the date has expired,
            if (today.after(reservation.getEnd())) {
                bookServiceProxy.deleteReservation(reservation);
            }
        }

        model.addAttribute("loans", loans);
        model.addAttribute("reservations", reservations);
        return"my_space";
    }

    @GetMapping("/my_space_already_extended")
    public String getSpaceExtendedError(Model model, @CookieValue("access_token") Cookie cookie){
        String login = CookieUtility.getLoginFromJWT(cookie.getValue());

        ResponseEntity<UserBookModel> res = oAuthServerProxy.getAccountByLogin(login);

        UserBookModel user = res.getBody();

        logger.warn("=====================================");
        logger.warn(user.getEmail());
        logger.warn("=====================================");

        ResponseEntity<List<LoanBean>> response = bookServiceProxy.getLoans(user.getEmail());
        List<LoanBean> loans = response.getBody();

        ResponseEntity<List<ReserveBean>> reservations = bookServiceProxy.getReservationByUserId(user.getId());

        model.addAttribute("loans", loans);
        model.addAttribute("reservations", reservations.getBody());
        return"my_space_already_extended";
    }

    @GetMapping("/loan/extend/{id}")
    public String extendLoan(@PathVariable("id")String id) {
        logger.warn("======================");
        logger.warn(bookServiceProxy.getLoan(id).toString());
        logger.warn("======================");

        ResponseEntity<LoanBean> res  = bookServiceProxy.getLoan(id);
        LoanBean loan = res.getBody();

        if (!loan.isExtended()) {
            loan.getEnd().add(Calendar.DAY_OF_MONTH, 14);
            loan.setExtended(true);
            bookServiceProxy.updateLoan(loan);
            return "redirect:/my_space";
        }
        else {
            return "redirect:/my_space_already_extended";
        }
    }

    @GetMapping("/loan/return/{id}")
    public String returnBook(@PathVariable("id") String id, @CookieValue("access_token") Cookie cookie) {
        String login = CookieUtility.getLoginFromJWT(cookie.getValue());
        ResponseEntity<UserBookModel> usr = oAuthServerProxy.getAccountByLogin(login);
        ResponseEntity<LoanBean> loan = bookServiceProxy.getLoan(id);
        BooksBean book = bookServiceProxy.getBookById(loan.getBody().getBook().getId());

        /*if (!bookServiceProxy.getBookById(loan.getBody().getBook().getId()).getUserListReservations().isEmpty()) {
            String nextUserEmail = bookServiceProxy.getBookById(loan.getBody().getBook().getId()).getUserListReservations().get(0).getEmail();
            batchServiceProxy.sendAcceptMail(nextUserEmail);
        }*/

        if (!book.getUserListReservations().isEmpty()) {
            // Send email to the first user of the list who asked.
            batchServiceProxy.sendAcceptMail(book.getUserListReservations().get(0).getEmail());
        }
        else {
            loan.getBody().getBook().setLeft(loan.getBody().getBook().getLeft() + 1);
            bookServiceProxy.updateBook(loan.getBody().getBook());
        }

        bookServiceProxy.deleteLoan(loan.getBody());

        return "redirect:/my_space";
    }

    @GetMapping("/reserve/cancel/{id}")
    public String cancelReservation(@PathVariable("id") String id) {
        ResponseEntity<ReserveBean> reserve = bookServiceProxy.getReservation(id);
        bookServiceProxy.updateBook(reserve.getBody().getBook());
        bookServiceProxy.deleteReservation(reserve.getBody());

        //TODO notify and email here for the next user if not null
        return "redirect:/my_space";
    }

    @GetMapping("/reserve/accept/{id}")
    public String acceptReservation(@PathVariable("id") String id) {
        ResponseEntity<ReserveBean> reserve = bookServiceProxy.getReservation(id);
        LoanBean loan = new LoanBean();

        loan.setBook(reserve.getBody().getBook());
        loan.setUser(reserve.getBody().getUser());
        loan.setBegin(new GregorianCalendar());
        GregorianCalendar end = new GregorianCalendar();
        end.add(Calendar.DAY_OF_MONTH, 14);
        loan.setEnd(end);

        bookServiceProxy.registerLoan(loan);
        bookServiceProxy.deleteReservation(reserve.getBody());
        return "redirect:/my_space";
    }
}
