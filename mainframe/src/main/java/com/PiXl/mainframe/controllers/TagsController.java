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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.entities.PostsEntity;

import com.PiXl.mainframe.models.Tags;
import com.PiXl.mainframe.services.TagsService;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@RequestMapping("/api/v1/tags")
@RestController
public class TagsController {
	@Autowired
	private TagsService tagsService;
	
	@GetMapping()
	public ResponseEntity<List<Tags>> getTags(){
		return ResponseEntity.ok(tagsService.getAllTags());
	}
	
	@GetMapping("/popular")
	public ResponseEntity<List<Tags>> getMostFreqTags(){
		return ResponseEntity.ok(tagsService.getMostFreqTags());
	}
	
	@GetMapping("/posts/{posts}")
	public ResponseEntity<List<Tags>> getTagsForPost(@PathVariable Set<PostsEntity> posts){
		return ResponseEntity.ok(tagsService.getAllTagsForPost(posts));
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Tags> getTag(@PathVariable Long id){
		Optional<Tags> tag = tagsService.getTagsById(id);
		return tag.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<List<Tags>> getTagsByName(@PathVariable String name){
		return ResponseEntity.ok(tagsService.getTags(name));
	}
	
	@PostMapping()
	public ResponseEntity<Tags> add(@PathVariable Tags tag){
		Tags tagToAddTags = tagsService.add(tag);
		if(tagToAddTags == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(tagToAddTags);
	}
	
	@PutMapping()
	public ResponseEntity<Tags> update(@PathVariable Tags tag){
		Tags tagToUpdateTags = tagsService.update(tag);
		if(tagToUpdateTags == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tagToUpdateTags);
	}
	
	@DeleteMapping()
	public ResponseEntity<Boolean> delete(@PathVariable Tags tag){
		boolean tagToDelete = tagsService.delete(tag);
		if(tagToDelete == false) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(tagToDelete);
	}
	
	
}
