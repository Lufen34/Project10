package com.openclassrooms.oauthserver;

import com.openclassrooms.oauthserver.model.ERoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*@Autowired
	private UserDetailsService userDetailsService;

	@Override
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth
				.parentAuthenticationManager(authenticationManagerBean())
				.userDetailsService(userDetailsService)
				.passwordEncoder(getPasswordEncoder());
    	auth
				.inMemoryAuthentication()
				.withUser("xavier")
				.password("test")
				.roles("USER");
    }

	@Override
    protected void configure(HttpSecurity http) throws Exception {
       http
       			.csrf().disable()
                .authorizeRequests()
	                .antMatchers("/")
	                    .permitAll()
	                .antMatchers("/register")
						.permitAll()
	                .antMatchers("/css/**", "/js/**", "/images/**")
	                    .permitAll()
	                .antMatchers("/login")
	                	.permitAll()
	                .anyRequest()
	                	.hasAnyAuthority(ERoles.USER.name(), ERoles.MODERATOR.name(), ERoles.ADMINISTRATOR.name())
	                .and()
	                .formLogin()
	                	.defaultSuccessUrl("/")
	                        .permitAll() 
	                .and()
	                .logout()
	                	.logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser("john.carnell").password("password1").roles("USER")
				.and()
				.withUser("william.woodward").password("password2").roles("USER", "ADMIN");
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return NoOpPasswordEncoder.getInstance();
	}
}