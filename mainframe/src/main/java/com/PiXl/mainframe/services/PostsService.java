package com.PiXl.mainframe.services;

import java.util.List;
import java.util.Optional;

import com.PiXl.mainframe.entities.PostsEntity;


public interface PostsService {
	List<PostsEntity> getAllPosts();
    Optional<PostsEntity> getPostById(Long postId);
    PostsEntity saveNewPost(PostsEntity post);
    PostsEntity editExistingPost(PostsEntity post);
    List<PostsEntity> getAllPostForUser(String userId);
    void deletePost(Long postId);
}
