package com.mctmodule4.BloggingPlatformAPI.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
    private String email;
    private String tokenValue;
}
