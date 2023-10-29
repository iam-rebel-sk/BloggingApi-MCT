package com.mctmodule4.BloggingPlatformAPI.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInInputDTO {


    @Pattern(regexp = "^.+@.+\\.(com)$", message = "Please enter valid email")
    private String email;
    private String password;
}
