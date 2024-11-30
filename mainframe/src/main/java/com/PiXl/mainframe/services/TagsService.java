package com.PiXl.mainframe.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;
import com.PiXl.mainframe.models.Tags;

import jakarta.validation.Valid;

public interface TagsService {
	
	/**
	 * Retrieve all tags from the repository.
	 *
	 * @return a list of all Tags objects available in the system
	 */
	List<TagsEntity> getAllTags();
	
	/**
	 * Retrieve a tag by its unique ID.
	 *
	 * @param id the unique identifier of the tag to retrieve
	 * @return an Optional containing the Tags object if found, or an empty Optional if no tag is found with the specified ID
	 */
	Optional<TagsEntity> getTagsById(Long id);
	
	/**
	 * Retrieve tags whose names contain the specified substring, ignoring case.
	 *
	 * @param name the substring to search for in tag names
	 * @return a list of Tags objects whose names contain the specified substring, ignoring case
	 */
	List<TagsEntity> getTags(String name);

	/**
	 * Retrieve all tags associated with a given set of posts.
	 *
	 * @param posts the set of PostsEntity objects to retrieve associated tags for
	 * @return a list of Tags objects that are associated with the specified posts
	 */
	List<TagsEntity> getAllTagsForPost(Set<PostsEntity> posts);
	
	/**
	 * Updates an existing tag in the repository
	 * @param tag - The tag to be updated
	 * @return tag - the updated tag object
	 */
	
	/**
	 * Gets the most frequent tags
	 * @return a list of the most frequent Tag objects 
	 */
	List<TagsEntity> getMostFreqTags();
	
	/**
	 * Updates an existing tag in the repository
	 * @param tag - The tag to be updated
	 * @return tag - the updated tag object
	 */
	TagsEntity update(@Valid TagsEntity tags);
	
    /**
     * Adds a new tag object
     * @param tag - the tag to be added 
     * @return tag - the added tag object
     */
	TagsEntity add(@Valid TagsEntity tags);
	
	/**
	 * Deletes an existing tag from the repository
	 * @param tag - The tag to be deleted
	 * @return true - the tag is successfully deleted
	 */
	boolean delete(@Valid TagsEntity tags);
	
	TagsEntity findOrCreateTagByName(String tagName);
	

}
