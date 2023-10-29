package com.mctmodule4.BloggingPlatformAPI.model.DTO;

import com.mctmodule4.BloggingPlatformAPI.model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostingDTO {
    private AuthenticationDTO authInfo;
    private Post newPost;
}
