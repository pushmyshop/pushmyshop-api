package com.github.pushmyshop.pushmyshopapi.config;

public class SecurityConstants {
    public static final String SECRET = "mypushyourshopsecret";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization %s";
    public static final String SIGN_UP_URL = "/api/customers";
}
