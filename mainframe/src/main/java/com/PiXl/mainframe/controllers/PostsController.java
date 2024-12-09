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
import org.springframework.web.bind.annotation.DeleteMapping;
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
@CrossOrigin
public class PostsController {

	@Autowired
	private PostsService postService;

	/**
     * Retrieves all posts from the system.
     *
     * @return A ResponseEntity containing a list of all PostsEntity objects if found,
     *         or a 404 (Not Found) status if no posts exist.
     */
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

	/**
     * Retrieves posts by a list of post IDs.
     *
     * @param ids A list of post IDs.
     * @return A ResponseEntity containing either:
     *         - A response with the found posts and a 200 (OK) status if posts are found.
     *         - A response with a 404 (Not Found) status if no posts match the provided IDs.
     */
	@RequestMapping(value = "/byIdList", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getAllPosts(@RequestBody List<Long> ids) {
		List<Posts> allPosts = postService.getPostsByIdList(ids);
		return ResponseHandler.generateResponse("All Posts by IDs", HttpStatus.OK, allPosts);
	}


	/**
     * Retrieves all posts for a given user.
     *
     * @param userId The ID of the user for whom to retrieve posts.
     * @return A ResponseEntity containing either:
     *         - A response with the found posts and a 200 (OK) status if posts exist for the user.
     *         - A response with a 404 (Not Found) status if no posts exist for the given user.
     */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getAllPostsForUser(@PathVariable String userId) {
		List<PostsEntity> allPosts = postService.getAllPostForUser(userId);
		return ResponseHandler.generateResponse("All Posts for UserId: " + userId, HttpStatus.OK, allPosts);
	}
	
	/**
     * Saves a new post to the system.
     *
     * @param media   The media file associated with the post (e.g., image).
     * @param userId  The ID of the user who created the post.
     * @param content The textual content of the post.
     * @param tags    A comma-separated list of tags for the post (optional).
     * @return A ResponseEntity indicating the outcome of the save operation:
     *         - 200 (OK) if the post is saved successfully.
     *         - 500 (Internal Server Error) if there is an issue saving the post.
     */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> savePost(@RequestParam("media") MultipartFile media,
			@RequestParam("userId") String userId,
			@RequestParam("content") String content,
			@RequestParam(value = "tag", required = false) String tags) {
		try {
			PostsEntity postToSave = new PostsEntity();
			HashSet<TagsEntity> tagsToSave = new HashSet<>();
			;
			if (!(tags.equals("") || tags.isEmpty() || tags.equals(null))) {
				for (String t : tags.split(",")) {
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
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	@GetMapping("/media/{filename}")
	public ResponseEntity<Resource> getMedia(@PathVariable String filename) throws MalformedURLException {
		Path filePath = Paths.get("uploads/" + filename);
		Resource resource = new UrlResource(filePath.toUri());
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(resource);
	}
	 /**
     * Edits an existing post.
     *
     * @param json A PostsEntity object with updated information.
     * @return A ResponseEntity containing:
     *         - 200 (OK) if the post is successfully edited.
     *         - 404 (Not Found) if the post could not be found or edited.
     */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> editPost(@RequestBody Map<String, String> json) {
		try {
			PostsEntity postToSave = postService.getPostById(Long.valueOf(json.get("postId")).longValue());
			HashSet<TagsEntity> tagsToSave = new HashSet<>();
			if (!(json.get("tag").equals("") || json.get("tag").isEmpty() || json.get("tag").equals(null))) {
				for (String t : json.get("tag").split(",")) {
					tagsToSave.add(new TagsEntity(t.strip()));
				}
			}
			postToSave.setContent(json.get("content"));
			PostsEntity editedPost = postService.editExistingPost(postToSave, tagsToSave);
			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, editedPost);
		} catch (FileStorageException e) {
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		} catch (Exception e) {
			return ResponseHandler.generateResponse("Unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR,
					e.getMessage());
		}
	}

	/**
     * Increments the like count on a given post.
     *
     * @param post A map containing the key "postId" with the ID of the post to like.
     * @return A ResponseEntity indicating that the post was liked (200 OK).
     */
	@RequestMapping(value = "/likePost", method = RequestMethod.POST)
	private ResponseEntity<Object> likePost(@RequestBody Map<String, Long> post) {
		postService.likePost(post.get("postId"));
		return ResponseHandler.generateResponse("Post Liked!", HttpStatus.OK);
	}

	/**
     * Decrements the like count on a given post.
     *
     * @param post A map containing the key "postId" with the ID of the post to unlike.
     * @return A ResponseEntity indicating that the post was unliked (200 OK).
     */
	@RequestMapping(value = "/unlikePost", method = RequestMethod.POST)
	private ResponseEntity<Object> unlikePost(@RequestBody Map<String, Long> post) {
		postService.unLikePost(post.get("postId"));
		return ResponseHandler.generateResponse("Post Unliked!", HttpStatus.OK);
	}

	/**
     * Retrieves the top liked posts up to a specified limit k. 
     * When a user reaches the end of a page scroll, we call this method to get us the
     * next next k posts to show to the user. It is a basic implementation of LAZY LOADING.
     *
     * @param limit The maximum number of posts to retrieve. Defaults to 10 if not specified.
     * @return A ResponseEntity containing either:
     *         - A list of the top liked posts (200 OK).
     *         - A 404 (Not Found) if no posts exist.
     */
	@RequestMapping(value = "/topPosts", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> getTopLikedPosts(@RequestParam(defaultValue = "10") Long limit) {
		List<Posts> topPosts = postService.getPostByMostLikes(limit); 
		if (topPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts", HttpStatus.NOT_FOUND);
		} else {
			return ResponseHandler.generateResponse("Top Liked Posts", HttpStatus.OK, topPosts);
		}
	}

	/**
     * Inserts multiple posts into the system by reading from a CSV file.
     * The file should be named "batch.csv" and located in the application's working directory.
     *
     * @return A ResponseEntity containing either:
     *         - A list of the inserted posts (200 OK).
     *         - A 404 (Not Found) if no posts were inserted.
     */
	@RequestMapping(value = "/batchInsert", method = RequestMethod.GET)
	@ResponseBody
	private ResponseEntity<Object> saveManyPosts() {
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

				PostsEntity postEn = new PostsEntity(userId, content, media, 0l, 0l);
				postEn.setTagsForPost(newTags);
				posts.add(postEn);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (posts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts to Insert", HttpStatus.NOT_FOUND);
		} else {
			List<PostsEntity> savedPosts = postService.savePostsInBatch(posts);
			return ResponseHandler.generateResponse("Inserted Posts", HttpStatus.OK, savedPosts);
		}
	}

	/**
     * Retrieves all posts that contain a specified tag.
     *
     * @param post A map containing the key "tagName" which is the name of the tag to filter by.
     * @return A ResponseEntity containing:
     *         - A list of posts containing the specified tag (200 OK).
     *         - A 404 (Not Found) if no posts contain the specified tag.
     */
	@RequestMapping(value = "/findPostsByTag", method = RequestMethod.POST)
	private ResponseEntity<Object> findPostsByTag(@RequestBody Map<String, String> post) {
		List<PostsEntity> filteredPosts = postService.getAllPostsWithTag(post.get("tagName"));
		if (filteredPosts.isEmpty()) {
			return ResponseHandler.generateResponse("No Posts for given tag", HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(filteredPosts);
		}
	}
	
	/**
	 * Deletes a post from the system.
	 *
	 * @param post The post to delete.
	 * @return A ResponseEntity containing a boolean indicating success or failure.
	 * - true (200 OK) if the post is successfully deleted.
     *         - A 404 (Not Found) if the post could not be found.
	 */
	@DeleteMapping()
    public ResponseEntity<Boolean> delete(@RequestParam Long postId) {
        boolean deletedPost = postService.deletePost(postId);
        if (deletedPost == false) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deletedPost);
    }
	
	
}
