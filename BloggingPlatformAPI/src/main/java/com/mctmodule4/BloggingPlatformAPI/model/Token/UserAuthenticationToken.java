package com.mctmodule4.BloggingPlatformAPI.model.Token;

import com.mctmodule4.BloggingPlatformAPI.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserAuthenticationToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer tokenId;
        private String tokenValue;
        private LocalDateTime tokenCreationTime;


    public UserAuthenticationToken(User existingUser) {
        this.user = existingUser;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }

    @OneToOne
    @JoinColumn(name = "fk_used_id")
    User user;
}
