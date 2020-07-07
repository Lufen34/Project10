package com.openclassroom.client;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.openclassroom.client.Controller.ClientController;
import com.openclassrooms.oauthserver.model.Account;
import com.openclassrooms.oauthserver.security.JwtProperties;
import com.openclassrooms.oauthserver.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    @Autowired
    private AccountService accountService;

    private static Logger logger = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the Authorization header, where the JWT Token should be
        String header = request.getHeader("Cookie");

        /*Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                logger.warn("Header: " + request.getHeader(headerNames.nextElement()));
            }
        }*/

        /*Cookie[] cookies = request.getCookies();
        String token = null;

        for (Cookie c: cookies) {
            if (c.getName().equals("access_token")) {
                token = c.getValue();
            }
        }*/

        // if header does not contain BEARER or is null delegate to Spring impl and exit
        if (header == null || !header.startsWith("access_token")){
            chain.doFilter(request, response);
            return;
        }

        // if header is present try grab user principal and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // continue filter execution
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Cookie");

        if (header != null){
            // parse the token and validate it
            String userName= JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(header.replace("access_token=", ""))
                    .getSubject();

            String token = header.replace("access_token=", "");
            // Recherche dans le token
            /*DecodedJWT decoded = JWT.decode(token);
            Claim t = decoded.getClaim("authorities");

            String test = t.asString();*/
            if (userName != null) {
                Claim tokenAuthority = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes())) // String
                        .build()
                        .verify(token)
                        .getClaim("authorities"); // ToString

                List<GrantedAuthority> authorities = new ArrayList<>();
                GrantedAuthority authority = new SimpleGrantedAuthority(tokenAuthority.asString());
                authorities.add(authority);
                return new UsernamePasswordAuthenticationToken(userName, null, authorities);
            }
            logger.warn("=====================================================");
            logger.warn("=====================================================");
            logger.error("RETURNS NULL CARE");
            logger.warn("=====================================================");
            logger.warn("=====================================================");
            return null;
        }
        logger.warn("=====================================================");
        logger.warn("=====================================================");
        logger.error("RETURNS NULL CARE");
        logger.warn("=====================================================");
        logger.warn("=====================================================");
        return null;
    }
}
