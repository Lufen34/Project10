package com.openclassrooms.oauthserver;

import com.openclassrooms.oauthserver.model.Account;
import com.openclassrooms.oauthserver.model.Authorities;
import com.openclassrooms.oauthserver.model.ERoles;
import com.openclassrooms.oauthserver.service.AccountService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody Account account) {
        try {
            logger.warn("==========================================");
            logger.warn(account.toString());
            logger.warn("==========================================");
            account.setRole(ERoles.USER);
            account.getAuthorities().add(new Authorities(ERoles.USER));
            account.setPassword(passwordEncoder.encode(account.getPassword()));
            if (accountService.findByLogin(account.getLogin()) != null || accountService.findByEmail(account.getEmail()) != null)
                return new ResponseEntity<>("Login or Email already exists.", HttpStatus.BAD_REQUEST);
            else
                accountService.save(account);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Login or Email already exists.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
    }
    @GetMapping(value = "/account/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccountByLogin(@PathVariable("login") String login) {
            if (accountService.findByLogin(login) != null)
                return new ResponseEntity<>(accountService.findByLogin(login), HttpStatus.OK);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/account/update")
    public ResponseEntity<String> updateAccount(@RequestBody Account account) {
        account.setRole(ERoles.USER);
        account.getAuthorities().add(new Authorities(ERoles.USER));
        accountService.updateAccount(account);
        return new ResponseEntity<String>("Successfully updated.", HttpStatus.OK);
    }
}
