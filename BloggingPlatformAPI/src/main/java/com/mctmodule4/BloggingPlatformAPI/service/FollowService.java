package com.mctmodule4.BloggingPlatformAPI.service;

import com.mctmodule4.BloggingPlatformAPI.model.Follow;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import com.mctmodule4.BloggingPlatformAPI.repo.IFollow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    IFollow followRepo;

    public void startToFollow(User follower, User targetUser) {

        Follow follow = new Follow(null, targetUser, follower);
        followRepo.save(follow);

    }

    public boolean findByTargetAndFollower(User follower, User targetUser) {
        List<Follow> follows =  followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,follower);
        return !follows.isEmpty();
    }

    public boolean authorisedToUnFollow(User tentativeFollower, User targetUser) {
        List<Follow> user = followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,tentativeFollower);
        return (user != null);
    }

    public void unFollowByTarget(User tentativeFollower ,User targetUser) {
        List<Follow> follow = followRepo.findByCurrentUserAndCurrentUserFollower(targetUser,tentativeFollower);
        followRepo.deleteAll(follow);
    }


}
