package com.domariev.apigateway.validator;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

import static com.domariev.apigateway.constatnts.SecurityConstants.WHITELISTED_URLS;

@Component
public class RouteValidator {

    public Predicate<ServerHttpRequest> isSecured = request -> WHITELISTED_URLS.stream()
            .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
