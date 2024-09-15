package org.una.programmingIII.WikiPets.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<CustomErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        CustomErrorResponse response = new CustomErrorResponse("Authentication Error", ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}
