package com.mctmodule4.BloggingPlatformAPI.service;

import com.mctmodule4.BloggingPlatformAPI.model.Like;
import com.mctmodule4.BloggingPlatformAPI.model.Post;
import com.mctmodule4.BloggingPlatformAPI.model.User;
import com.mctmodule4.BloggingPlatformAPI.repo.ILike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    @Autowired
    ILike likeRepo;

    public boolean isAlreadyLiked(Post postToBeLiked, User liker) {
        List<Like> likes =  likeRepo.findByPostAndLiker(postToBeLiked,liker);

        return (likes!=null && !likes.isEmpty());

    }

    public void addLike(Like newLike) {
        likeRepo.save(newLike);
    }

    public List<Like> getAllLikesInAPost(Post post) {
        return likeRepo.findByPost(post);
    }

    public String removeLikeByLikerAndPost(Post postToBeUnliked, User liker) {

        List<Like> likes = likeRepo.findByPostAndLiker(postToBeUnliked, liker);
        if(likes != null ){
            likeRepo.deleteAll(likes);
            return "Unliked";
        }
        else {
            return "You have not liked this post yet";
        }
    }

    public void deleteLikeInThePost(Post existingPost) {

        List<Like> likes = likeRepo.findByPost(existingPost);
        likeRepo.deleteAll(likes);
    }
}

