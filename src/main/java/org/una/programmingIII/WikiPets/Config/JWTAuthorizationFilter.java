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
import org.una.programmingIII.WikiPets.Service.JWTService.TokenPurpose;

import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private final JWTService jwtService;
    private final PublicGraphQLOperations publicGraphQLOperations = new PublicGraphQLOperations();

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

        if (requestURI.equals("/graphiql") || isPublicGraphQLRequest(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        if (header == null || !header.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
            return;
        }

        String token = header.substring(7);
        try {
            if (!jwtService.isTokenValid(token, TokenPurpose.ACCESS)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
            authenticate(token);
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            logger.error("Error to validate JWT", e);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Login and password recovery are the only operations allowed without a token. The body is
     * readable here because GraphQLRequestFilter caches it for POST /graphql.
     */
    private boolean isPublicGraphQLRequest(HttpServletRequest request) throws IOException {
        if (!request.getMethod().equalsIgnoreCase("POST") || !request.getRequestURI().equals("/graphql")) {
            return false;
        }
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        return publicGraphQLOperations.isPublic(body);
    }

    private void authenticate(String token) {
        String email = jwtService.getEmailFromToken(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
