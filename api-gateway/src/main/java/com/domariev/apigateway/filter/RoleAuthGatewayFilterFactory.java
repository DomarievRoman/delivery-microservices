package com.domariev.apigateway.filter;

import com.domariev.apigateway.exception.InvalidAuthorizationHeaderException;
import com.domariev.apigateway.validator.JwtValidator;
import com.domariev.apigateway.validator.RouteValidator;
import lombok.Data;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.domariev.apigateway.constatnts.SecurityConstants.BEARER;
import static com.domariev.apigateway.constatnts.SecurityConstants.TOKEN_BEGIN_INDEX;

@Component
public class RoleAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<RoleAuthGatewayFilterFactory.Config> {

    private final RouteValidator routeValidator;
    private final JwtValidator jwtValidator;

    @Data
    public static class Config {
        private List<String> roles;
    }

    public RoleAuthGatewayFilterFactory(RouteValidator routeValidator, JwtValidator jwtValidator) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.jwtValidator = jwtValidator;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            if (routeValidator.isSecured.test(request)) {
                HttpHeaders headers = request.getHeaders();
                if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new InvalidAuthorizationHeaderException("No authorization header provided");
                }
                String authHeader = Objects.requireNonNull(headers.get(HttpHeaders.AUTHORIZATION)).get(0);
                if (!authHeader.startsWith(BEARER)) {
                    throw new InvalidAuthorizationHeaderException("Invalid authorization header");
                }
                authHeader = authHeader.substring(TOKEN_BEGIN_INDEX);
                jwtValidator.validateToken(authHeader);
                jwtValidator.checkUserAuthority(authHeader, config.getRoles());
            }
            return chain.filter(exchange);
        });
    }

}
