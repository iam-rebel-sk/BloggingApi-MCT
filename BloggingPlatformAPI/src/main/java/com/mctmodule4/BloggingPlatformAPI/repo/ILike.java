package com.mctmodule4.BloggingPlatformAPI.repo;

import com.mctmodule4.BloggingPlatformAPI.model.Like;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ILike extends JpaRepository<Like,Integer> {
    List<Like> findByPostAndLiker(Post postToBeLiked, User liker);

    List<Like> findByPost(Post post);
}
