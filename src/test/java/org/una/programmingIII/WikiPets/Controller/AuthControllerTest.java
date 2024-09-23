//package org.una.programmingIII.WikiPets.Controller;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.graphql.test.tester.GraphQlTester;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.una.programmingIII.WikiPets.Dto.UserDto;
//import org.una.programmingIII.WikiPets.Input.LogInInput;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AuthControllerTest {
//
//    @Autowired
//    private GraphQlTester graphQlTester;
//
//    @Mock
//    private AuthenticationService authenticationService;
//
//    @Mock
//    private JWTService jwtService;
//
//    @Mock
//    private RefreshTokenService refreshTokenService;
//
//    @InjectMocks
//    private AuthController authController;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testLoginSuccess() {
//        LogInInput input = new LogInInput("test@example.com", "password");
//        UserDto userDto = new UserDto(); // Assume UserDto has a default constructor
//        when(authenticationService.authenticate(input.getEmail(), input.getPassword())).thenReturn(userDto);
//        when(jwtService.generateAccessToken(any())).thenReturn("access-token");
//        when(jwtService.generateRefreshToken(any())).thenReturn("refresh-token");
//
//        graphQlTester.document("""
//            mutation Login($input: LogInInput!) {
//                login(input: $input) {
//                    userDto {
//                        // fields you want to verify
//                    }
//                    accessToken
//                    refreshToken
//                }
//            }
//            """)
//                .variable("input", input)
//                .execute()
//                .path("login.accessToken").entity(String.class).isEqualTo("access-token");
//    }
//
//    @Test
//    void testLoginFailure() {
//        LogInInput input = new LogInInput("test@example.com", "wrong-password");
//        when(authenticationService.authenticate(input.getEmail(), input.getPassword())).thenThrow(new BadCredentialsException("Invalid credentials"));
//
//        graphQlTester.document("""
//            mutation Login($input: LogInInput!) {
//                login(input: $input) {
//                    userDto {
//                        // fields you want to verify
//                    }
//                    accessToken
//                    refreshToken
//                }
//            }
//            """)
//                .variable("input", input)
//                .execute()
//                .errors()
//                .satisfyAnyErrorMessageContaining("Invalid credentials");
//    }
//
//    // Similar tests for other methods like refreshToken, changePassword, etc.
//}
