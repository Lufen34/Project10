package com.openclassroom.client.Controller;

import com.openclassroom.client.BookServiceBeans.LoanBean;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.Calendar;
import java.util.List;

@Controller
public class MySpace {

    @Autowired
    BookServiceProxy bookServiceProxy;

    @Autowired
    private OAuthServerProxy oAuthServerProxy;

    private static Logger logger = LoggerFactory.getLogger(MySpace.class);

    @GetMapping("/my_space")
    public String getSpace(Model model, @CookieValue("access_token") Cookie cookie){
        String login = CookieUtility.getLoginFromJWT(cookie.getValue());

        ResponseEntity<UserBookModel> res = oAuthServerProxy.getAccountByLogin(login);

        UserBookModel user = res.getBody();

        logger.warn("=====================================");
        logger.warn(user.getEmail());
        logger.warn("=====================================");

        ResponseEntity<List<LoanBean>> response = bookServiceProxy.getLoans(user.getEmail());
        List<LoanBean> loans = response.getBody();

        model.addAttribute("loans", loans);
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

        model.addAttribute("loans", loans);
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
}
