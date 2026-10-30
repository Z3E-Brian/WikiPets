package org.una.programmingIII.WikiPets.Config;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.una.programmingIII.WikiPets.Model.User;
import org.una.programmingIII.WikiPets.Service.JWTService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Runs a request through the same filters SecurityConfig registers (GraphQLRequestFilter and then
 * JWTAuthorizationFilter) and records whether it reached the GraphQL endpoint behind them.
 */
class JWTAuthorizationFilterTest {

    private static final String TEST_SECRET = "test-only-secret-key-with-at-least-32-bytes!";
    private static final long ONE_MINUTE = 60_000;

    private JWTService jwtService;
    private User user;

    private boolean endpointReached;
    private String bodySeenByEndpoint;
    private String authenticatedEmail;

    private final HttpServlet graphQLEndpoint = new HttpServlet() {
        @Override
        protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
            endpointReached = true;
            bodySeenByEndpoint = new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            authenticatedEmail = authentication == null ? null : authentication.getName();
        }
    };

    @BeforeEach
    void setUp() {
        jwtService = new JWTService(TEST_SECRET, ONE_MINUTE, ONE_MINUTE, ONE_MINUTE);
        user = new User();
        user.setEmail("user@example.com");
        endpointReached = false;
        bodySeenByEndpoint = null;
        authenticatedEmail = null;
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void loginMutationWithoutTokenReachesEndpointWithItsBody() throws Exception {
        String body = graphQLBody("mutation { login(input: { email: \\\"user@example.com\\\", password: \\\"secret\\\" }) { token } }");

        MockHttpServletResponse response = send(body, null);

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(endpointReached);
        assertEquals(body, bodySeenByEndpoint);
    }

    @Test
    void passwordRecoveryMutationsWithoutTokenReachEndpoint() throws Exception {
        send(graphQLBody("mutation { initiateRecoverPassword(email: \\\"user@example.com\\\") }"), null);
        assertTrue(endpointReached);

        endpointReached = false;
        send(graphQLBody("mutation { recoverPassword(input: { email: \\\"user@example.com\\\", password: \\\"new\\\", token: \\\"t\\\" }) }"), null);
        assertTrue(endpointReached);
    }

    @Test
    void loginTextInsideCommentDoesNotSkipAuthentication() throws Exception {
        String body = graphQLBody("# login(input: { \\n query { getUsers { id email } }");

        MockHttpServletResponse response = send(body, null);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void loginTextInsideStringArgumentDoesNotSkipAuthentication() throws Exception {
        String body = graphQLBody("mutation { deleteUser(id: 1, note: \\\"mutation { login\\\") }");

        MockHttpServletResponse response = send(body, null);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void operationNamedLoginThatRunsAnotherFieldDoesNotSkipAuthentication() throws Exception {
        String body = "{\"operationName\":\"login\",\"query\":\"mutation login { deleteUser(id: 1) }\"}";

        MockHttpServletResponse response = send(body, null);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void invalidTokenIsRejectedWithoutReachingEndpoint() throws Exception {
        MockHttpServletResponse response = send(graphQLBody("query { getUsers { id } }"), "not-a-valid-token");

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void refreshTokenIsNotAcceptedAsAccessToken() throws Exception {
        MockHttpServletResponse response = send(graphQLBody("query { getUsers { id } }"), jwtService.generateRefreshToken(user));

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void passwordResetTokenIsNotAcceptedAsAccessToken() throws Exception {
        String resetToken = jwtService.generatePasswordResetToken("user@example.com");

        MockHttpServletResponse response = send(graphQLBody("query { getUsers { id } }"), resetToken);

        assertEquals(HttpServletResponse.SC_UNAUTHORIZED, response.getStatus());
        assertFalse(endpointReached);
    }

    @Test
    void validAccessTokenReachesEndpointAuthenticated() throws Exception {
        MockHttpServletResponse response = send(graphQLBody("query { getUsers { id } }"), jwtService.generateAccessToken(user));

        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
        assertTrue(endpointReached);
        assertEquals("user@example.com", authenticatedEmail);
    }

    private MockHttpServletResponse send(String body, String bearerToken) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("POST", "/graphql");
        request.setContentType("application/json");
        request.setContent(body.getBytes(StandardCharsets.UTF_8));
        if (bearerToken != null) {
            request.addHeader("Authorization", "Bearer " + bearerToken);
        }
        MockHttpServletResponse response = new MockHttpServletResponse();
        new MockFilterChain(graphQLEndpoint, new GraphQLRequestFilter(), new JWTAuthorizationFilter(jwtService))
                .doFilter(request, response);
        return response;
    }

    /**
     * Wraps a GraphQL document (already escaped for JSON) in the body Spring GraphQL expects.
     */
    private static String graphQLBody(String escapedQuery) {
        return "{\"query\":\"" + escapedQuery + "\"}";
    }
}
