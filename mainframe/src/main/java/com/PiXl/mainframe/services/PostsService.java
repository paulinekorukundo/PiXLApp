package com.PiXl.mainframe.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;
import com.PiXl.mainframe.models.Posts;


public interface PostsService {
	
	// GENERAL POSTS
	List<PostsEntity> getAllPosts();
    Posts getPostById(Long postId);
    Posts savePost(Posts post);
    PostsEntity saveNewPost(PostsEntity post, Set<TagsEntity> tags, MultipartFile file);
    PostsEntity editExistingPost(PostsEntity post);
    void deletePost(Long postId);
    
    // USER SPECIFIC POSTS
    List<PostsEntity> getAllPostForUser(String userId);
    
    // POSTS UTILITY
    List<Posts> getPostByMostLikes(long numOfPosts);
    List<Posts> getPostsByIdList(List<Long> ids);
    
    // POST INTERACTION
    void likePost(Long postId);
    void unLikePost(Long postId);
    
    /*
     * void commentPost(Long postId, Comment commentData);
     * void deleteComment(Long postId, Long commentId);
     * List<Comment> getCommentsOnPost(Long postId);
     * */
    
    // CONTENT DISCOVERY
    List<Posts> getAllPostsWithTag(String tagName);
    public List<Posts> searchPosts(String query);
    
    // Batch Insert
    List<PostsEntity> savePostsInBatch(List<PostsEntity> posts);
}
