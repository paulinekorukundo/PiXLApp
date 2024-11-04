package com.PiXl.mainframe.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.models.Posts;
import com.PiXl.mainframe.services.PostsService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostsController {
	
	@Autowired
	private PostsService postService;
	
	
	@GetMapping("/")
	private ResponseEntity<Object> getAllPosts(){
		List<Posts> allPosts = postService.getAllPosts();
		if(allPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("All Posts", HttpStatus.OK, allPosts);
		}
	}
	
	@GetMapping("/{userid}")
	private ResponseEntity<Object> getAllPostsForUser(@PathVariable String userid){
		List<Posts> allPosts = postService.getAllPostForUser(userid);
		if(allPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("All Posts for UserId: " + userid, HttpStatus.OK, allPosts);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> savePost(@RequestBody Posts json){
		Posts savedPost = postService.saveNewPost(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> editPost(@RequestBody Posts json){
		Posts savedPost = postService.editExistingPost(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error editing post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Edited!", HttpStatus.OK, savedPost);
		}
	}
	
	
	// Method to show the top k liked posts for pagination.
	// When a user reaches the end of a page scroll, we call this method to get us the 
	// next k posts to show to the user. It is a basic implementation of LAZY LOADING.
	@RequestMapping(value = "/likedPosts", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getTopLikedPosts(@RequestBody Map<String, Long> json){
		List<Posts> topPosts = postService.getPostByMostLikes(json.get("numPosts"));
		if(topPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Top Liked Posts", HttpStatus.OK, topPosts);
		}
	}
}
