package com.PiXl.mainframe.Services;

import java.util.List;
import java.util.Optional;

import com.PiXl.mainframe.Entities.PostsEntity;


public interface PostsService {
	List<PostsEntity> getAllPosts();
    Optional<PostsEntity> getPostById(Long postId);
    PostsEntity saveNewPost(PostsEntity post);
    PostsEntity editExistingPost(PostsEntity post);
    List<PostsEntity> getAllPostForUser(String userId);
    void deletePost(Long postId);
}
