package com.mctmodule4.BloggingPlatformAPI.repo;

import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPost extends JpaRepository<Post, Integer> {
    List<Post> findByPostOwner(User existingUser);
}
