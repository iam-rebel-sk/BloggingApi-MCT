package com.mctmodule4.BloggingPlatformAPI.repo;

import com.mctmodule4.BloggingPlatformAPI.model.Comment;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IComment extends JpaRepository<Comment, Integer> {
    List<Comment> findByPost(Post post);
}
