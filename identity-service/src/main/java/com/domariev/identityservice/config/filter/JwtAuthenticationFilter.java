package com.domariev.identityservice.config.filter;

import com.domariev.identityservice.exception.ApiError;
import com.domariev.identityservice.exception.InvalidTokenException;
import com.domariev.identityservice.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.domariev.identityservice.config.constants.SecurityConstants.*;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        String jwt;
        String userEmail;
        if (Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authorizationHeader.substring(TOKEN_BEGIN_INDEX);
        try {
            userEmail = jwtService.extractUsername(jwt);
        } catch (InvalidTokenException ex) {
            HttpStatus forbiddenStatus = HttpStatus.UNAUTHORIZED;
            ApiError apiError = new ApiError(request.getRequestURI(),
                    ex.getMessage(),
                    forbiddenStatus.getReasonPhrase(),
                    forbiddenStatus.value(),
                    LocalDateTime.now());
            handleFilterException(forbiddenStatus, response, apiError);
            return;
        }

        authenticateUser(request, jwt, userEmail);

        filterChain.doFilter(request, response);
    }

    private void handleFilterException(HttpStatus status,
                                       HttpServletResponse response,
                                       ApiError apiError) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(new ObjectMapper().writeValueAsString(apiError));
    }

    private void authenticateUser(HttpServletRequest request, String jwt, String userEmail) {
        if (Objects.nonNull(userEmail) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
    }
}
