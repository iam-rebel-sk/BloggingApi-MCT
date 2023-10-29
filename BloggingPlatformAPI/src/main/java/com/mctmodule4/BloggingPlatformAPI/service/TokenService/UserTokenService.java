package com.mctmodule4.BloggingPlatformAPI.service.TokenService;

import com.mctmodule4.BloggingPlatformAPI.model.DTO.AuthenticationDTO;
import com.mctmodule4.BloggingPlatformAPI.model.Token.UserAuthenticationToken;
import com.mctmodule4.BloggingPlatformAPI.repo.token.IUserAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {
    @Autowired
    private IUserAuthenticationToken userTokenRepo;

    public  boolean authentic(AuthenticationDTO authInfo) {
        String email = authInfo.getEmail();
        String tokenValue = authInfo.getTokenValue();

        UserAuthenticationToken token = userTokenRepo.findFirstByTokenValue(tokenValue);
        if(token != null){
            return token.getUser().getUserEmail().equals(email);
        }
        else {
            return  false;
        }
    }

    public void createNewToken(UserAuthenticationToken token) {
        userTokenRepo.save(token);
    }

    public void deleteToken(String tokenValue) {
        UserAuthenticationToken token = userTokenRepo.findFirstByTokenValue(tokenValue);
        userTokenRepo.delete(token);
    }
}
