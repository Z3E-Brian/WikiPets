package org.una.programmingIII.WikiPets.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.stream.Collectors;

public class GraphQLRequestFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(GraphQLRequestFilter.class);
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().equals("/graphql")) {
            CachedBodyHttpServletRequest cachedBodyRequest = new CachedBodyHttpServletRequest(request);
            String body = cachedBodyRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            logger.info("GraphQL request body: {}", body);

            if (body.contains("\"operationName\":\"login\"") || body.contains("mutation { login")||body.contains("login(input: { ")) {
                logger.info("Login operation detected, skipping authentication");
                filterChain.doFilter(cachedBodyRequest, response);
                cachedBodyRequest.clearBody();
                return;
            }
            filterChain.doFilter(cachedBodyRequest, response);
            cachedBodyRequest.clearBody();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}