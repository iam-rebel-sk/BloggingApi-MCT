package com.mctmodule4.BloggingPlatformAPI.service;

import com.mctmodule4.BloggingPlatformAPI.model.Comment;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.AuthenticationDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.CommentDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.PostingDTO;
import com.mctmodule4.BloggingPlatformAPI.model.DTO.SignInInputDTO;
import com.mctmodule4.BloggingPlatformAPI.model.Like;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.Token.UserAuthenticationToken;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import com.mctmodule4.BloggingPlatformAPI.repo.IUser;
import com.mctmodule4.BloggingPlatformAPI.service.TokenService.UserTokenService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    IUser userRepo;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    PostService postService;

    @Autowired
    LikeService likeService;

    @Autowired
    CommentService commentService;

    @Autowired
    FollowService followService;

    public String userSignUp(@NotNull User newUser) {
        String email = newUser.getUserEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);
        if (existingUser != null){
            return "User already exists. Please sign in";
        }

        String newPassword = newUser.getUserPassword();
        String confirmPassword = newUser.getConfirmPassword();

        if (!newPassword.equals(confirmPassword)){
            return "Password did not matched.";
        }

        try {
            String encryptedPassword = PasswordEncryptor.encrypt(newPassword);
            newUser.setConfirmPassword(encryptedPassword);
            newUser.setUserPassword(encryptedPassword);

            newUser.setPostList(null);
            userRepo.save(newUser);
            return "Sign up successful.";

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue. Please try again later";
        }
    }

    public String userSignIn(@NotNull SignInInputDTO signInInfo) {
        String email = signInInfo.getEmail();
        User existingUser = userRepo.findFirstByUserEmail(email);
        if (existingUser == null){
            return "User not registered. Please sign up first";
        }

        String password = signInInfo.getPassword();
        try {
            String encryptedPassword = PasswordEncryptor.encrypt(password);
            if (existingUser.getUserPassword().equals(encryptedPassword)){

                UserAuthenticationToken token = new UserAuthenticationToken(existingUser);
                userTokenService.createNewToken(token);
                System.out.println("Your token value is: "+ token.getTokenValue());
                return "Sign in successful";

            }
            else {
                return "Unauthenticated access";
            }

        } catch (NoSuchAlgorithmException e) {
            return "Internal server issue. Please try again later";
        }
    }

    public String userSignOut(AuthenticationDTO authInfo) {
        if (userTokenService.authentic(authInfo)) {
            String tokenValue = authInfo.getTokenValue();
            userTokenService.deleteToken(tokenValue);
            return "Sign out successful";

        }
        else {
            return "Unauthenticated access!";
        }


    }

    public String createNewPost(@NotNull PostingDTO postInfo) {
        AuthenticationDTO authInfo = postInfo.getAuthInfo();
        Post newPost = postInfo.getNewPost();

        if (userTokenService.authentic(authInfo)){

            String email = authInfo.getEmail();
            User existingUser = userRepo.findFirstByUserEmail(email);

            newPost.setPostOwner(existingUser);
            newPost.setLikes(null);
            newPost.setPostCreatedTimeStamp(LocalDateTime.now());

            postService.createNewPost(newPost);
            return "Your " + newPost.getPostType()+ " has posted successfully";
        }
        else {
            return "Unauthenticated access";
        }
    }

    public List<Post> getAllPost() {

        return postService.getAllPost();
    }

    public List<Post> getAllPostByUserId(Integer userId) {
        User user =  userRepo.findById(userId).orElseThrow();
        return  user.getPostList();
    }

    public Post getPostById(Integer postId) {
        return postService.getPostById(postId);
    }

    public String likePost(AuthenticationDTO authInfo, Integer postId) {
        if (userTokenService.authentic(authInfo)){

            String email = authInfo.getEmail();

            Post postToBeLiked = postService.getPostById(postId);

            User liker = userRepo.findFirstByUserEmail(email);

            boolean alreadyLiked = likeService.isAlreadyLiked(postToBeLiked, liker);

            if(!alreadyLiked) {
                Like newLike = new Like(null, postToBeLiked, liker);

                likeService.addLike(newLike);

                return liker.getUserHandle() + " liked " +  postId;
            }
            else {
                return "already liked";
            }

        }
        else {
            return "Unauthenticated access";
        }
    }

    public String addComment(CommentDTO commentDetails, Integer postId) {
        AuthenticationDTO authInfo = commentDetails.getAuthInfo();
        if (userTokenService.authentic(authInfo)){

            Post postToBeCommented = postService.getPostById(postId);

            User commentator = userRepo.findFirstByUserEmail(authInfo.getEmail());

            String commentBody = commentDetails.getCommentBody();

            Comment newComment = new Comment(null,commentBody,
                    LocalDateTime.now(), postToBeCommented, commentator);

            commentService.addNewComment(newComment);
            return commentator.getUserHandle() + " commented on " + postId;

        }
        else {
            return "Unauthenticated access";
        }
    }

    public String getAllLikesInAPost(Integer postId) {

        Post post = postService.getPostById(postId);
        List<Like> likes = likeService.getAllLikesInAPost(post);
        return "Total Likes on post no : " + postId + " is " + likes.size();
    }

    public List<Comment> getAllComments(Integer postId) {

        Post post = postService.getPostById(postId);
        List<Comment> comments = commentService.getAllComments(post);
        return comments;
    }

    public String removeLike(AuthenticationDTO authInfo, Integer postId) {
        if (userTokenService.authentic(authInfo)){
            Post postToBeUnliked = postService.getPostById(postId);

            User liker = userRepo.findFirstByUserEmail(authInfo.getEmail());

            return likeService.removeLikeByLikerAndPost(postToBeUnliked,liker);
        }
        else {
            return "unauthenticated access";
        }

    }

    public String removeCommentByCommentId(AuthenticationDTO authInfo, Integer commentId) {

        if (userTokenService.authentic(authInfo)){

            String email = authInfo.getEmail();

            Comment comment = commentService.findCommentById(commentId);

            Post postOfTheDeletableComment = comment.getPost();

            if(authorizedCommentRemover(email, postOfTheDeletableComment, comment)){
                commentService.deleteCommentById(commentId);
                return "Comment deleted";
            }
            else {
                return "Not authorised";
            }

        }
        else {
            return "Unauthenticated access";
        }

    }

    private boolean authorizedCommentRemover(String email, Post postOfTheDeletableComment, Comment comment) {
        User potentialRemover = userRepo.findFirstByUserEmail(email);
        return potentialRemover.equals(postOfTheDeletableComment.getPostOwner()) || potentialRemover.equals(comment.getCommentator());

    }

    public String removePostByPostId(AuthenticationDTO authInfo, Integer postId) {

        if(userTokenService.authentic(authInfo)){

            Post post = postService.getPostById(postId);

            String  postOwnerEmail = post.getPostOwner().getUserEmail();

            String email = authInfo.getEmail();

            if(email.equals(postOwnerEmail))
            {

                postService.removeById(postId);
                return "post removed!!";

            }
            else {
                return "Un authorized access!!";
            }

        }
        else {
            return "Unauthenticated access";
        }
    }

    public String updateYourPost(PostingDTO postInfo, Integer postId) {

        AuthenticationDTO authInfo = postInfo.getAuthInfo();
        Post post = postInfo.getNewPost();
        if (userTokenService.authentic(authInfo)){
            User existingUser = userRepo.findFirstByUserEmail(authInfo.getEmail());
            Post existingPost = postService.getPostById(postId);
            if (existingUser.equals(existingPost.getPostOwner())){
                existingPost.setPostCaption(post.getPostCaption());
                existingPost.setPostLocation(post.getPostLocation());
                postService.updatePost(existingPost);
                return "Your post has updated";
            }
            else {
                return "Unauthorised access";
            }
        }
        else{
            return "Unauthenticated access";
        }
    }

    public String updateComment(CommentDTO commentInfo, Integer commentId) {
        AuthenticationDTO authInfo = commentInfo.getAuthInfo();
        String commentBody = commentInfo.getCommentBody();

        if (userTokenService.authentic(authInfo)){
            Comment existingComment = commentService.findCommentById(commentId);
            User existingCommentator = userRepo.findFirstByUserEmail(authInfo.getEmail());

            if (existingCommentator.equals(existingComment.getCommentator())){
                existingComment.setCommentator(existingCommentator);
                existingComment.setCommentBody(commentBody);
                commentService.updateComment(existingComment);
                return "Your comment updated";
            }
            else {
                return "Unauthorised access";
            }
        }
        else {
            return "Unauthenticated access";
        }

    }

    public String followTargetUserByUserId(AuthenticationDTO authInfo, Integer targetUserId) {

        if (userTokenService.authentic(authInfo)){

            User follower =  userRepo.findFirstByUserEmail(authInfo.getEmail());
            User targetUser = userRepo.findById(targetUserId).orElseThrow();

            if (authorisedToFollow(follower, targetUser)){
                followService.startToFollow(follower,targetUser);
                return follower.getUserHandle() + " started following " + targetUser.getUserHandle();
            }
            else {
                return "Unauthorised access";
            }
        }
        else {
            return "Unauthenticated access";
        }
    }

    private boolean authorisedToFollow(User follower, User targetUser) {

        boolean isFollower = followService.findByTargetAndFollower(follower,targetUser );

        return !isFollower && !follower.equals(targetUser);
    }

    public String unFollow(AuthenticationDTO authInfo, Integer targetUserId) {
        if (userTokenService.authentic(authInfo)){
            User tentativeFollower = userRepo.findFirstByUserEmail(authInfo.getEmail());
            User targetUser = userRepo.findById(targetUserId).orElseThrow();

            if (followService.authorisedToUnFollow(tentativeFollower,targetUser)){

                followService.unFollowByTarget(tentativeFollower,targetUser);
                return "You have unfollowed to " + targetUser.getUserHandle();

            }
            else {
                return "Unauthorised access";
            }
        }
        else {
            return "Unauthenticated access";
        }
    }

}
