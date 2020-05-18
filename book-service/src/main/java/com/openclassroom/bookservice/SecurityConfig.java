package com.openclassroom.bookservice;

import com.openclassroom.bookservice.Model.ERoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {    

    /* Managed by Spring container to link with my @Service impl of UserDetailService */
	@Autowired
	private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
    		.userDetailsService(userDetailsService)
    		.passwordEncoder(getPasswordEncoder());
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
       http
       			.csrf().disable()
				.authorizeRequests()
	                .antMatchers("/users")
	                	.hasAnyAuthority(ERoles.USER.name(), ERoles.MODERATOR.name(), ERoles.ADMINISTRATOR.name())
	                .antMatchers("/")
	                    .permitAll()
	                .antMatchers("/register")
						.permitAll()
	                .antMatchers("/save_user")
	                	.permitAll()
	                .antMatchers("/css/**", "/js/**", "/images/**")
	                    .permitAll()
	                .antMatchers("/login")
	                	.permitAll()
	                .anyRequest()
	                	.hasAnyAuthority(ERoles.USER.name(), ERoles.MODERATOR.name(), ERoles.ADMINISTRATOR.name())
					.and()
					/*.anyRequest()
						.permitAll()
						.and()*/
	                .formLogin()
	                	.defaultSuccessUrl("/")
	                        .permitAll() 
	                .and()
	                .logout()
	                	.logoutSuccessUrl("/");
                    
    	//http.csrf().disable().authorizeRequests().anyRequest().permitAll();

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}