package com.mctmodule4.BloggingPlatformAPI.service;

import com.mctmodule4.BloggingPlatformAPI.model.Comment;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.repo.IComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    IComment commentRepo;


    public void addNewComment(Comment newComment) {
        commentRepo.save(newComment);
    }

    public List<Comment> getAllComments(Post post) {
        return commentRepo.findByPost(post);
    }

    public Comment findCommentById(Integer commentId) {
        return commentRepo.findById(commentId).orElseThrow();
    }

    public void deleteCommentById(Integer commentId) {
        commentRepo.deleteById(commentId);
    }

    public void deleteCommentInThePost(Post existingPost) {
        List<Comment> comments = commentRepo.findByPost(existingPost);
        commentRepo.deleteAll(comments);
    }

    public void updateComment(Comment existingComment) {
        commentRepo.save(existingComment);
    }
}
