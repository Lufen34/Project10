package com.openclassroom.client.utilities;

import com.auth0.jwt.JWT;
import org.springframework.web.util.WebUtils;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtility {

    /*
    The lifetime of a refresh token is up to the (AS) authorization server
    they can expire, be revoked, etc. The difference between a refresh token and an access token is the audience:
    the refresh token only goes back to the authorization server, the access token goes to the (RS) resource server.
     */
    public final static String HEADER_ACCESS_TOKEN = "access_token";

    public static Cookie generateCookie(String token) {
        Cookie cookie = new Cookie(HEADER_ACCESS_TOKEN, token);
        cookie.setDomain("localhost");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");

        return cookie;
    }

    public static void clearCookie(HttpServletResponse httpServletResponse){
        // According to DZone this is how to clear a cookie
        Cookie cookie = new Cookie(HEADER_ACCESS_TOKEN, null);
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        httpServletResponse.addCookie(cookie);
    }

    public static String getValue(HttpServletRequest httpServletRequest, String name) {
        Cookie cookie = WebUtils.getCookie(httpServletRequest, name);
        return cookie != null ? cookie.getValue() : null;
    }

    public static String getLoginFromJWT(String token){
        String login = JWT.decode(token).getSubject();
        return login;
    }

    public static String getIdFromJWT(String token){
        String id = JWT.decode(token).getClaim("id").asString();
        return id;
    }
}
