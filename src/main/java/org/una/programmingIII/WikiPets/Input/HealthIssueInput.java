package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthIssueInput {
    private Long id;
    private String name;
    private String description;
    private String symptoms;
    private String treatment;
}
