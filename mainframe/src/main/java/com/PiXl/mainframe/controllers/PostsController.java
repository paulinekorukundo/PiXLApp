package com.PiXl.mainframe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
