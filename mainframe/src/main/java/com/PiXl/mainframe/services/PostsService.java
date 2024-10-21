package com.PiXl.mainframe.services;

import java.util.List;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.models.Posts;


public interface PostsService {
	List<Posts> getAllPosts();
    Posts getPostById(Long postId);
    PostsEntity saveNewPost(PostsEntity post);
    PostsEntity editExistingPost(PostsEntity post);
    List<Posts> getAllPostForUser(String userId);
    void deletePost(Long postId);
}
