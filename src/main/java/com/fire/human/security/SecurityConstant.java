package com.fire.human.security;

public class SecurityConstant {

    public static final String LOGIN_URL = "/api/persons/login";
    public static final String REGISTER_URL = "/api/persons";
    public static final String H2_URL = "/h2-console/**";
    public static final String SECRET = "MySecretKeyJWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 60_60_000;

}
