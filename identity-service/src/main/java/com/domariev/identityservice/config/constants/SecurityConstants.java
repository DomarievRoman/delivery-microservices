package com.domariev.identityservice.config.constants;

import lombok.experimental.UtilityClass;

import static com.domariev.identityservice.model.RoleAuthority.*;
import static com.domariev.identityservice.model.RoleAuthority.COURIER;

@UtilityClass
public class SecurityConstants {

    public static final String[] WHITELISTED_URLS = {"/auth/user/**", "/error", "/swagger-ui/**",
            "/v3/api-docs/**"};
    public static final String ADMIN_URLS = "/management/admin/**";
    public static final String[] USER_MANAGEMENT_URLS = {"/management/user/**", "/user/payment/**"};
    public static final String[] ANY_USER_AUTHORITY = {CUSTOMER.getName(), COOK.getName(), MANAGER.getName(), COURIER.getName()};

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final int TOKEN_BEGIN_INDEX = 7;


}
