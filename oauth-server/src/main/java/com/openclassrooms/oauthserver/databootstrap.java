package com.openclassrooms.oauthserver;

import com.openclassrooms.oauthserver.model.Account;
import com.openclassrooms.oauthserver.model.Authorities;
import com.openclassrooms.oauthserver.model.ERoles;
import com.openclassrooms.oauthserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class databootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        init();
    }

    public void init(){
        Authorities auth = new Authorities();
        auth.setAuthority(ERoles.USER.name());
        Account test = new Account();
        test.setName("alf");
        test.setLogin("alfred");
        test.setPassword(passwordEncoder.encode("test"));
        test.setRole(ERoles.USER);
        test.setAddress("we don't care");
        test.getAuthorities().add(auth);
        if (accountService.findByLogin(test.getLogin()) == null)
            accountService.save(test);
    }
}
