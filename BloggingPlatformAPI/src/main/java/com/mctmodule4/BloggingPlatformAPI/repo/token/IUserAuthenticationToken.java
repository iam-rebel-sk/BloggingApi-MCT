package com.mctmodule4.BloggingPlatformAPI.repo.token;

import com.mctmodule4.BloggingPlatformAPI.model.Token.UserAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthenticationToken extends JpaRepository<UserAuthenticationToken, Integer> {
    UserAuthenticationToken findFirstByTokenValue(String tokenValue);
}
