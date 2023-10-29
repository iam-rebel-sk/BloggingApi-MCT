package com.mctmodule4.BloggingPlatformAPI.repo;

import com.mctmodule4.BloggingPlatformAPI.model.Follow;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFollow extends JpaRepository<Follow, Integer> {
    List<Follow> findByCurrentUserAndCurrentUserFollower(User targetUser, User follower);


}
