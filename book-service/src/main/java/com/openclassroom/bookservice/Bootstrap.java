package com.openclassroom.bookservice;

import java.util.Arrays;
import java.util.List;

import com.openclassroom.bookservice.Model.Authorities;
import com.openclassroom.bookservice.Model.ERoles;
import com.openclassroom.bookservice.Model.User;
import com.openclassroom.bookservice.Service.AuthoritiesService;
import com.openclassroom.bookservice.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
    private UserService uService;
    
	@Autowired
	private AuthoritiesService aService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		init();
	}
	
	public void init() {
		if (uService.findByLogin("admin") == null) {
            List<Authorities> authorities = Arrays.asList(new Authorities(ERoles.ADMINISTRATOR));
            User user = new User("Julie", "7 treacy meadows", "admin", new BCryptPasswordEncoder().encode("admin"), authorities);
            uService.save(user);
		}
	}

}