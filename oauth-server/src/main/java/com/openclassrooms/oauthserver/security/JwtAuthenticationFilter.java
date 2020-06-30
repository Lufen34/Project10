package com.openclassrooms.oauthserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.oauthserver.model.Account;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.authentication.BadCredentialsException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    /*
        grab data from our own type of request
        is triggered when we perform a POST to /login
        we need to pass a JSON such as {"username" : "login", "password" : "mypassword"} in the request body
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //grab credentials and map them to Account
        Account credentials = null;
        try {
            credentials = new ObjectMapper().readValue(request.getInputStream(), Account.class);
        }catch (IOException e){
            e.printStackTrace();
        }

        /*  create login token, this token will be used by spring security to verify if we are authenticated with our credentials
            this will not be returned to the user
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                credentials.getLogin(),
                credentials.getPassword(),
                credentials.getAuthorities());
        // Authenticate user
        try {
            return  authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Error, wrong credentials.");
        }
    }

    //If successfull, build the JWT token
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Grab principal
        Account principal = (Account)authResult.getPrincipal();

        // Create JWT token
        String token = JWT.create()
                .withSubject(principal.getLogin())
                .withClaim("authorities", principal.getAuthorities().get(0).getAuthority())
                .withClaim("role", principal.getRole().name())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()));

        // Add token is response
        response.addHeader(JwtProperties.HEADER_STRING, JwtProperties.TOKEN_PREFIX + " " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.TEXT_PLAIN_VALUE);
        response.getWriter().print(failed.getLocalizedMessage());
        response.getWriter().flush();
    }
}
