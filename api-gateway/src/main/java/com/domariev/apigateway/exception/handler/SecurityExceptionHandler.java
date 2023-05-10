package com.domariev.apigateway.exception.handler;

import com.domariev.apigateway.exception.ApiError;
import com.domariev.apigateway.exception.ForbiddenException;
import com.domariev.apigateway.exception.InvalidAuthorizationHeaderException;
import com.domariev.apigateway.exception.InvalidTokenException;
import com.domariev.apigateway.util.DataBufferWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SecurityExceptionHandler implements ErrorWebExceptionHandler {

    private final DataBufferWriter bufferWriter;

    @Override
    @SneakyThrows
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ApiError apiError;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ServerHttpResponse response = exchange.getResponse();
        if (ex instanceof ForbiddenException) {
            status = HttpStatus.FORBIDDEN;
            apiError = buildApiError(exchange, ex.getMessage(), status);
        } else if (ex instanceof InvalidTokenException) {
            ApiError apiError1 = new ObjectMapper().readValue(ex.getMessage(), ApiError.class);
            status = HttpStatus.UNAUTHORIZED;
            apiError = buildApiError(exchange, apiError1.message().toString(), status);
        } else if (ex instanceof InvalidAuthorizationHeaderException) {
            status = HttpStatus.UNAUTHORIZED;
            apiError = buildApiError(exchange, ex.getMessage(), status);
        } else {
            apiError = new ApiError(status.getReasonPhrase(), status.value(), LocalDateTime.now());
        }

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.setStatusCode(status);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return bufferWriter.write(response, apiError);
    }

    private static ApiError buildApiError(ServerWebExchange exchange, String message, HttpStatus status) {
        return new ApiError(exchange.getRequest().getURI().getPath(),
                message,
                status.getReasonPhrase(),
                status.value(),
                LocalDateTime.now());
    }

}
