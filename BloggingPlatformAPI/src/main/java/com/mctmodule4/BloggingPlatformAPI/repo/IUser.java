package com.mctmodule4.BloggingPlatformAPI.repo;

import com.mctmodule4.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUser extends JpaRepository<User, Integer> {
    User findFirstByUserEmail(String email);

}
