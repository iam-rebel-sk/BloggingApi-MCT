package com.mctmodule4.BloggingPlatformAPI.service;

import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import com.mctmodule4.BloggingPlatformAPI.repo.IPost;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    IPost postRepo;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    public void createNewPost(Post newPost) {

        postRepo.save(newPost);
    }

    public List<Post> getAllPost() {
        return postRepo.findAll();
    }

    public List<Post> getAllPostByUserHandle(User existingUser) {

       List<Post> posts = postRepo.findByPostOwner(existingUser);
       return posts;
    }

    public Post getPostById(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow();
        return post;
    }

    @Transactional
    public void removeById(Integer postId) {

        Post existingPost = postRepo.findById(postId).orElseThrow();

        likeService.deleteLikeInThePost(existingPost);

        commentService.deleteCommentInThePost(existingPost);

        postRepo.deleteById(postId);

    }

    public void updatePost(Post existingPost) {
        postRepo.save(existingPost);
    }
}
