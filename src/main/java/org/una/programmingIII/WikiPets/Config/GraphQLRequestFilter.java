package org.una.programmingIII.WikiPets.Config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Caches the body of POST /graphql requests so JWTAuthorizationFilter can read it to detect the
 * public operations and the GraphQL handler can still read it afterwards.
 */
public class GraphQLRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if (request.getMethod().equalsIgnoreCase("POST") && request.getRequestURI().equals("/graphql")) {
            CachedBodyHttpServletRequest cachedBodyRequest = new CachedBodyHttpServletRequest(request);
            filterChain.doFilter(cachedBodyRequest, response);
            cachedBodyRequest.clearBody();
        } else {
            filterChain.doFilter(request, response);
        }
    }
}