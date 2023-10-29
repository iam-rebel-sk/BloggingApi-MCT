package com.mctmodule4.BloggingPlatformAPI.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.PrivateKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private AuthenticationDTO authInfo;
    private  String commentBody;
}
