package com.domariev.apigateway.validator;

import com.domariev.apigateway.exception.ForbiddenException;
import com.domariev.apigateway.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

import static com.domariev.apigateway.constatnts.SecurityConstants.RETRIEVE_USER_AUTHORITIES_URL;
import static com.domariev.apigateway.constatnts.SecurityConstants.VALIDATE_TOKEN_URL;

@Component
@RequiredArgsConstructor
public class JwtValidator {

    private final RestTemplate restTemplate;

    public void validateToken(String token) {
        try {
            restTemplate.getForObject(VALIDATE_TOKEN_URL.concat(token), Boolean.class);
        } catch (HttpClientErrorException exception) {
            throw new InvalidTokenException(exception.getResponseBodyAsString());
        }
    }

    @SuppressWarnings("unchecked")
    public void checkUserAuthority(String token, List<String> roles) {
        try {
            List<String> userRoles = restTemplate.getForObject(RETRIEVE_USER_AUTHORITIES_URL.concat(token), List.class);
            if (Objects.nonNull(userRoles) && userRoles.stream().noneMatch(roles::contains)) {
                throw new ForbiddenException("You have no permissions to access resource");
            }
        } catch (Exception exception) {
            throw new ForbiddenException("You have no permissions to access resource");
        }
    }

}
