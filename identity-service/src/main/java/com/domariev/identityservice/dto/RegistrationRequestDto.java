package com.domariev.identityservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

    @NotBlank(message = "Email can not be blank")
    @Email(message = "Provided invalid email")
    private String email;
    @NotBlank(message = "Password can not be blank")
    @Size(min = 5, message = "Password too short")
    private String password;

}
