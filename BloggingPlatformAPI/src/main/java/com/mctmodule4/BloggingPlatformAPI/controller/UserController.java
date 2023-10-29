package com.mctmodule4.BloggingPlatformAPI.controller;

import com.mctmodule4.BloggingPlatformAPI.model.Comment;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.AuthenticationDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.CommentDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.PostingDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.SignInInputDTO;
import com.mctmodule4.BloggingPlatformAPI.model.Like;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import com.mctmodule4.BloggingPlatformAPI.service.UserService;
import jakarta.servlet.http.PushBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("user/sign up")
    public String userSignUp(@Valid @RequestBody User newUser) {
        return userService.userSignUp(newUser);
    }

    @PostMapping("user/sign in")
    public String userSignIn(@Valid @RequestBody SignInInputDTO signInInfo) {
        return userService.userSignIn(signInInfo);
    }
    @PostMapping("new/post")
    public String createNewPost(@RequestBody PostingDTO postInfo) {
        return userService.createNewPost(postInfo);
    }

    @PostMapping("like/{postId}")
    public String likePost(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer postId){
        return  userService.likePost(authInfo, postId);
    }

    @PostMapping("comment/post/{postId}")
    public String addComment(@RequestBody CommentDTO commentDetails, @PathVariable Integer postId)
    {
        return userService.addComment(commentDetails,postId);
    }

    @PostMapping("follow/{targetUserId}")
    public String followTargetUser(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer targetUserId){
        return userService.followTargetUserByUserId(authInfo, targetUserId);
    }

    @PutMapping("post/{postId}")
    public String updatePost(@RequestBody PostingDTO postInfo, @PathVariable Integer postId){
        return userService.updateYourPost(postInfo, postId);
    }

    @PutMapping("comment/{commentId}")
    public String updateComment(@RequestBody CommentDTO commentInfo, @PathVariable Integer commentId){
        return userService.updateComment(commentInfo, commentId);
    }



    @GetMapping("user/{userId}/posts")
    public List<Post> getAllPostByUserHandle(@PathVariable Integer userId) {
        return userService.getAllPostByUserId(userId);
    }

    @GetMapping("post/{postId}")
    public Post getPostById(@PathVariable Integer postId){
        return  userService.getPostById(postId);
    }

    @GetMapping("likes/post/{postId}")
    public String getAllLikesInAPost(@PathVariable Integer postId){
        return userService.getAllLikesInAPost(postId);
    }
    @GetMapping("comments/post/{postId}")
    public List<Comment> getAllComments(@PathVariable Integer postId)
    {
        return userService.getAllComments(postId);
    }





    @DeleteMapping("user/sign out")
    public String userSignOut(@RequestBody AuthenticationDTO authInfo) {
        return userService.userSignOut(authInfo);
    }

    @DeleteMapping("unlike/post/{postId}")
    public String removeLike(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer postId){
        return userService.removeLike(authInfo,postId);
    }

    @DeleteMapping("post/comment/{commentId}")
    public String removeCommentByCommentId(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer commentId){
        return userService.removeCommentByCommentId(authInfo,commentId);
    }

    @DeleteMapping("user/post/{postId}")
    public String removePostByPostId(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer postId){
        return userService.removePostByPostId(authInfo, postId);
    }

    @DeleteMapping("unfollow/user/{targetUserId}")
    public String unFollow(@RequestBody AuthenticationDTO authInfo, @PathVariable Integer targetUserId){
        return userService.unFollow(authInfo, targetUserId);
    }


}
