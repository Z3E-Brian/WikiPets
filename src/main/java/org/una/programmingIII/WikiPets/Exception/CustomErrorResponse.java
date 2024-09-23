package org.una.programmingIII.WikiPets.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private String error;
    private String message;
    private int status;
    private LocalDateTime timestamp;
}
