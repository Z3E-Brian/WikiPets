package org.una.programmingIII.WikiPets.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.una.programmingIII.WikiPets.Service.JWTService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;

    public JWTAuthorizationFilter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {
    String requestURI = request.getRequestURI();
    String header = request.getHeader("Authorization");

    if (requestURI.equals("/graphiql") || isLoginRequest(request)) {
        filterChain.doFilter(request, response);
        return;
    }

    if (header == null || !header.startsWith("Bearer ")) {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
        return;
    }

    String token = header.substring(7);
    Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    try {
        if (jwtService.validateToken(token)) {
            handleTokenValidation(request, response, token);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        }
    } catch (Exception e) {
        SecurityContextHolder.clearContext();
        logger.error("Error to validate JWT", e);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
    }

    filterChain.doFilter(request, response);
}

private boolean isLoginRequest(HttpServletRequest request) throws IOException {
    String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    return body.contains("\"operationName\":\"login\"") || body.contains("mutation { login") || body.contains("login(input: { ");
}

private void handleTokenValidation(HttpServletRequest request, HttpServletResponse response, String token) throws IOException {
    if (!jwtService.isTokenExpired(token)) {
        String refreshToken = request.getHeader("Refresh-Token");
        if (refreshToken != null && jwtService.validateToken(refreshToken)) {
            String email = jwtService.getEmailFromToken(refreshToken);
            String newAccessToken = jwtService.generateAccessToken(email);
            response.setHeader("Authorization", "Bearer " + newAccessToken);
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh token has expired or is invalid");
        }
    } else {
        String email = jwtService.getEmailFromToken(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
}