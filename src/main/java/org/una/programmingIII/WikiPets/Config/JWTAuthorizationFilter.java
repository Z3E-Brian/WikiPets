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
        if (requestURI.equals("/graphiql")) {
            filterChain.doFilter(request, response);
            return;
        }
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {

            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            if (body.contains("\"operationName\":\"login\"") || body.contains("mutation { login")||body.contains("login(input: { ")) {
                filterChain.doFilter(request, response);
                return;
            }

            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        String token = header.substring(7);
        Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

        try {
            if (jwtService.validateToken(token)) {
                if (jwtService.isAccessTokenExpired(token)) {
                    String refreshToken = request.getHeader("Refresh-Token");
                    if (refreshToken != null && jwtService.validateToken(refreshToken)) {
                        String email = jwtService.getEmailFromToken(refreshToken);
                        String newAccessToken = jwtService.generateAccessToken(email);
                        response.setHeader("Authorization", "Bearer " + newAccessToken);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Refresh token has expired or is invalid");
                        return;
                    }
                } else {
                    String email = jwtService.getEmailFromToken(token);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            email, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            logger.error("Error to validate JWT", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}