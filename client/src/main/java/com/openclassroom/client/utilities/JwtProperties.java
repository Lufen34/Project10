package com.openclassroom.client.utilities;

public class JwtProperties {
    public static final String SECRET = "openclassroom";
    public static final int EXPIRATION_TIME = 864000000; // 10 days in miliseconds
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
}
