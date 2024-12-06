package com.PiXl.mainframe.controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.PiXl.mainframe.exceptions.FileStorageException;
import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;
import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.models.Posts;
import com.PiXl.mainframe.services.PostsService;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = "http://localhost:5173")
public class PostsController {
	
	@Autowired
	private PostsService postService;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	
	private ResponseEntity<List<PostsEntity>> getAllPosts() {
	    List<PostsEntity> allPosts = postService.getAllPosts();
	    if (allPosts.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	    } else {
	        return ResponseEntity.ok(allPosts); 
	    }
	}
//	private ResponseEntity<Object> getAllPosts(){
//		List<Posts> allPosts = postService.getAllPosts();
//		if(allPosts.isEmpty()) {
//			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
//		}else {
//			return ResponseHandler.generateResponse("All Posts", HttpStatus.OK, allPosts);
//		}
//	}
	
	@RequestMapping(value = "/byIdList", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getAllPosts(@RequestBody List<Long> ids){
		List<Posts> allPosts = postService.getPostsByIdList(ids);
		if(allPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("All Posts by IDs", HttpStatus.OK, allPosts);
		}
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getAllPostsForUser(@PathVariable String userId){
		List<PostsEntity> allPosts = postService.getAllPostForUser(userId);
		if(allPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("All Posts for UserId: " + userId, HttpStatus.OK, allPosts);
		}
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savePost(@RequestParam("media") MultipartFile media,
	                                       @RequestParam("userId") String userId,
	                                       @RequestParam("content") String content,
	                                       @RequestParam(value = "tag", required = false) String tags) {
	    try {
	    	PostsEntity postToSave = new PostsEntity();
	    	HashSet<TagsEntity> tagsToSave = new HashSet<>();;
	    	if(!(tags.equals("") || tags.isEmpty() || tags.equals(null))) {
				for(String t : tags.split(",")) {
					tagsToSave.add(new TagsEntity(t.strip()));
				}
			}
	    	postToSave.setUserId(userId);
	    	postToSave.setContent(content);
	    	postToSave.setMedia(null);
	    	postToSave.setCommentsCount(0l);
			postToSave.setLikesCount(0l);
	    	PostsEntity savedPost = postService.saveNewPost(postToSave, tagsToSave, media);
	        return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
	    } catch (FileStorageException e) {
	        return ResponseHandler.generateResponse("Error saving post.", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	    } catch (Exception e) {
	        return ResponseHandler.generateResponse("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
	    }
	}
	
//	private ResponseEntity<Object> savePost(@RequestBody Map<String, String> json){
//		HashSet<TagsEntity> tagsToSave = new HashSet<>();
//		PostsEntity postToSave = new PostsEntity();
//		if(!json.get("tags").equals("")) {
//			String[] tags = json.get("tags").split(",");
//			for(String t : tags) {
//				tagsToSave.add(new TagsEntity(t.strip()));
//			}
//		}
//		postToSave.setUserId(json.get("userId"));
//		postToSave.setContent(json.get("content"));
//		postToSave.setMedia(json.get("media"));
//		postToSave.setCommentsCount(0l);
//		postToSave.setLikesCount(0l);
//		
//		PostsEntity savedPost = postService.saveNewPost(postToSave, tagsToSave);
//		if(savedPost == null) {
//			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.NOT_FOUND);
//		}else {
//			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
//		}
//	}

	@GetMapping("/media/{filename}")
	public ResponseEntity<Resource> getMedia(@PathVariable String filename) throws MalformedURLException {
	    Path filePath = Paths.get("uploads/" + filename);
	    Resource resource = new UrlResource(filePath.toUri());
	    return ResponseEntity.ok()
	                         .contentType(MediaType.IMAGE_JPEG)
	                         .body(resource);
	}
	
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> editPost(@RequestBody PostsEntity json){
		PostsEntity savedPost = postService.editExistingPost(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error editing post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Edited!", HttpStatus.OK, savedPost);
		}
	}
	
	@RequestMapping(value = "/likePost", method = RequestMethod.POST)
	private ResponseEntity<Object> likePost(@RequestBody Map<String, Long> post){
		postService.likePost(post.get("postId"));
		return ResponseHandler.generateResponse("Post Liked!", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/unlikePost", method = RequestMethod.POST)
	private ResponseEntity<Object> unlikePost(@RequestBody Map<String, Long> post){
		postService.unLikePost(post.get("postId"));
		return ResponseHandler.generateResponse("Post Unliked!", HttpStatus.OK);
	}
	
	// Method to show the top k liked posts for pagination.
	// When a user reaches the end of a page scroll, we call this method to get us the 
	// next k posts to show to the user. It is a basic implementation of LAZY LOADING.
	@RequestMapping(value = "/topPosts", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getTopLikedPosts(@RequestBody Map<String, Long> json){
		List<Posts> topPosts = postService.getPostByMostLikes(json.get("numPosts"));
		if(topPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Top Liked Posts", HttpStatus.OK, topPosts);
		}
	}
	
	@RequestMapping(value = "/batchInsert", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> saveManyPosts(){
		List<PostsEntity> posts = new ArrayList<>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("batch.csv"));
			reader.readLine();
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] tokens = line.split(",");
				String userId = tokens[0].strip();
				String content = tokens[1].strip();
				String media = tokens[2].strip();
				HashSet<TagsEntity> newTags = Arrays.stream(tokens[3].split(";"))
                        .map(tag -> new TagsEntity(tag.strip()))
                        .collect(Collectors.toCollection(HashSet::new));
				
				PostsEntity postEn = new PostsEntity(userId, content, media, 0l,0l);
				postEn.setTagsForPost(newTags);
				posts.add(postEn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(posts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts to Insert", HttpStatus.NOT_FOUND);
		}else {
			List<PostsEntity> savedPosts = postService.savePostsInBatch(posts);
			return ResponseHandler.generateResponse("Inserted Posts", HttpStatus.OK, savedPosts);
		}
	}
	
	@RequestMapping(value = "/findPostsByTag", method = RequestMethod.POST)
	private ResponseEntity<Object> findPostsByTag(@RequestBody Map<String, String> post){
		List<PostsEntity> filteredPosts = postService.getAllPostsWithTag(post.get("tagName"));
		if(filteredPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts for given tag", HttpStatus.NOT_FOUND);
		}else {
//			return ResponseHandler.generateResponse("Filtered Posts", HttpStatus.OK, filteredPosts);
			
			return ResponseEntity.ok(filteredPosts);
		}
	}
}
