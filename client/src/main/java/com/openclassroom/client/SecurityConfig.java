package com.openclassroom.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /*
    Cross-Origin Resource Sharing (CORS) is a mechanism that uses additional HTTP headers
    to tell browsers to give a web application running at one origin, access to selected
    resources from a different origin.
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                /*.cors() // peut etre Très important !
                .and()*/
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager()))
                .authorizeRequests()
                // configure access rules
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.GET, "/my_space").hasAuthority("USER")
                .antMatchers("/css/**", "/js/**", "/images/**").permitAll()
                //.anyRequest().hasAuthority("USER")
                .anyRequest().permitAll()
                .and()
                .logout().disable()
                .formLogin().disable();

    }

    /*
    source : https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/cors.html
     */

    /*@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}