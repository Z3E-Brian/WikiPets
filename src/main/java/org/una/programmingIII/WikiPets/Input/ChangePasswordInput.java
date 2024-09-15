package org.una.programmingIII.WikiPets.Input;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordInput {
private String email;
private String password;
private String newPassword;
}
