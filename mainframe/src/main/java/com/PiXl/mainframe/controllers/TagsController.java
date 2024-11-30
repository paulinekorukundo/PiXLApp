package com.PiXl.mainframe.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;
import com.PiXl.mainframe.models.Tags;
import com.PiXl.mainframe.services.TagsService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestMapping("/api/v1/tags")
@RestController
public class TagsController {
	@Autowired
	private TagsService tagsService;
	
	/**
     * Retrieves all tags.
     *
     * @return A ResponseEntity containing a List of Tags
     */
	@GetMapping()
	public ResponseEntity<List<TagsEntity>> getTags(){
		return ResponseEntity.ok(tagsService.getAllTags());
	}
	
	/**
     * Retrieves the most frequent tags.
     *
     * @return A ResponseEntity containing a List of Tags sorted by frequency.
     */
	@GetMapping("/popular")
	public ResponseEntity<List<TagsEntity>> getMostFreqTags(){
		return ResponseEntity.ok(tagsService.getMostFreqTags());
	}
	
	/**
     * Retrieves all tags associated with the given posts.
     *
     * @param posts A Set of PostsEntity objects.
     * @return A ResponseEntity containing a List of Tags associated with the posts.
     */
	@GetMapping("/posts/{posts}")
	public ResponseEntity<List<TagsEntity>> getTagsForPost(@PathVariable Set<PostsEntity> posts){
		return ResponseEntity.ok(tagsService.getAllTagsForPost(posts));
	}
	
	/**
     * Retrieves a tag by its ID.
     *
     * @param id The ID of the tag to retrieve.
     * @return A ResponseEntity containing the Tag if found, or a 404 response if not found.
     */
	@GetMapping("/id/{id}")
	public ResponseEntity<TagsEntity> getTag(@PathVariable Long id){
		Optional<TagsEntity> tag = tagsService.getTagsById(id);
		return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	 /**
     * Retrieves all tags whose name matches the given name.
     *
     * @param name The partial name to match tags against.
     * @return A ResponseEntity containing a List of matching Tags.
     */
	@GetMapping("/name/{name}")
	public ResponseEntity<List<TagsEntity>> getTagsByName(@PathVariable String name){
		return ResponseEntity.ok(tagsService.getTags(name));
	}
	
	/**
     * Adds a new tag to the system.
     *
     * @param tag The new tag to be added.
     * @return A ResponseEntity containing the newly created Tag.
     */
	@PostMapping()
	public ResponseEntity<TagsEntity> add(@RequestBody TagsEntity tag){
		TagsEntity tagToAddTags = tagsService.add(tag);
		return ResponseEntity.ok(tagToAddTags);
	}
	
	/**
     * Updates an existing tag in the system.
     *
     * @param tag The updated tag details.
     * @return A ResponseEntity containing the updated Tag if successful, or a 404 response if not found.
     */
	@PutMapping()
	public ResponseEntity<TagsEntity> update(@RequestBody TagsEntity tag){
		TagsEntity tagToUpdateTags = tagsService.update(tag);
		if(tagToUpdateTags == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tagToUpdateTags);
	}
	
	/**
     * Deletes a tag from the system.
     *
     * @param tag The tag details to delete.
     * @return A ResponseEntity containing a boolean indicating success or failure.
     */
	@DeleteMapping()
	public ResponseEntity<Boolean> delete(@RequestBody TagsEntity tag){
		boolean tagToDelete = tagsService.delete(tag);
		if(tagToDelete == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tagToDelete);
	}
	
	
}
