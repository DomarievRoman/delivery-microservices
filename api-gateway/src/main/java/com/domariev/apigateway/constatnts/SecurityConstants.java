package com.domariev.apigateway.constatnts;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SecurityConstants {

    public static final List<String> WHITELISTED_URLS = List.of("/auth/user", "/eureka");
    public static final String VALIDATE_TOKEN_URL = "http://identity-service/auth/user/token/valid/";
    public static final String RETRIEVE_USER_AUTHORITIES_URL = "http://identity-service/auth/user/token/authorities/";
    public static final String BEARER = "Bearer ";
    public static final int TOKEN_BEGIN_INDEX = 7;

}
