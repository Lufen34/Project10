package com.openclassrooms.oauthserver.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.openclassrooms.oauthserver.model.Account;
import com.openclassrooms.oauthserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// Confirmed : Will be implemented on all the needed services
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private AccountService accountService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT Token should be
        String header = request.getHeader(JwtProperties.HEADER_STRING);

        // if header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith(JwtProperties.HEADER_STRING)){
            chain.doFilter(request, response);
            return;
        }

        // if header is present try grab user principal from database and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // conitunue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER_STRING);

        if (token != null){
            // parse the token and validate it
            String userName= JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            // find in DB is we find user with the token's subjects (username)
            // then grab, user details and create a spring auth token using username, password, authorities/roles
            if (userName != null) {
                Account account = accountService.findByLogin(userName);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null, account.getAuthorities());
                return auth;
            }
            return null;
        }
        return null;
    }
}
