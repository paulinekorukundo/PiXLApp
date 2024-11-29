package com.PiXl.mainframe.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.PiXl.mainframe.models.Posts;


public interface PostsService {
	
	// GENERAL POSTS
	List<Posts> getAllPosts();
    Posts getPostById(Long postId);
    Posts savePost(Posts post);
    Posts saveNewPost(MultipartFile file, String userId, String content, String tagName);
    
    Posts editExistingPost(Posts post);
    void deletePost(Long postId);
    
    // USER SPECIFIC POSTS
    List<Posts> getAllPostForUser(String userId);
    
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
}
