package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;

import com.PiXl.mainframe.models.Tags;
import com.PiXl.mainframe.repositories.TagsRepository;
import com.PiXl.mainframe.services.TagsService;

import jakarta.validation.Valid;

@Service
public class TagsServiceImpl implements TagsService {

	@Autowired
	private TagsRepository tagsRepository;

	/**
	 * Retrieve all tags from the repository.
	 *
	 * @return a list of all Tags objects available in the system
	 */
	@Override
	public List<TagsEntity> getAllTags() {
		var tagsEntities = (List<TagsEntity>) tagsRepository.findAll();
		// return from(tagsEntities);
		return tagsEntities;
	}

	/**
	 * Retrieve all tags associated with a given set of posts.
	 *
	 * @param posts the set of PostsEntity objects to retrieve associated tags for
	 * @return a list of Tags objects that are associated with the specified posts
	 */
	@Override
	public List<TagsEntity> getAllTagsForPost(Set<PostsEntity> posts) {
		// var tagsEntities = tagsRepository.findByPostsContainingIgnoreCase(posts);
		// return from(tagsEntities);
		return null;
	}

	/**
	 * Retrieve a tag by its unique ID.
	 *
	 * @param id the unique identifier of the tag to retrieve
	 * @return an Optional containing the Tags object if found, or an empty Optional
	 *         if no tag is found with the specified ID
	 */
	@Override
	public Optional<TagsEntity> getTagsById(Long id) {
		var te = tagsRepository.findById(id);
		Optional<TagsEntity> result = te.isPresent() ? Optional.of(te.get()) : Optional.empty();
		return result;
	}

	/**
	 * Retrieve tags whose names contain the specified substring, ignoring case.
	 *
	 * @param name the substring to search for in tag names
	 * @return a list of Tags objects whose names contain the specified substring,
	 *         ignoring case
	 */
	@Override
	public List<TagsEntity> getTags(String name) {
		var tagsEntities = tagsRepository.findByNameContainingIgnoreCase(name);
		return tagsEntities;
	}

	/**
	 * Gets the most frequent tags
	 * 
	 * @return a list of the most frequent Tag objects
	 */
	public List<TagsEntity> getMostFreqTags() {
		var tagsEntities = tagsRepository.findMostFrequentTags();
		return tagsEntities;
	}

	/**
	 * Adds a new tag object
	 * 
	 * @param tag - the tag to be added
	 * @return tag - the added tag object
	 */
	@Override
	public TagsEntity add(@Valid TagsEntity tags) {
		TagsEntity addedTagsEntity = tagsRepository.save(tags);
		return addedTagsEntity;
	}

	/**
	 * Updates an existing tag in the repository
	 * 
	 * @param tag - The tag to be updated
	 * @return tag - the updated tag object
	 */
	@Override
	public TagsEntity update(@Valid TagsEntity tags) {
		TagsEntity updatedTagsEntity = null;
		if (tagsRepository.existsById(tags.getTagId())) {
			updatedTagsEntity = tagsRepository.save(tags);
		}
		return updatedTagsEntity;
	}

	/**
	 * Deletes an existing tag from the repository
	 * 
	 * @param tag - The tag to be deleted
	 * @return true - the tag is successfully deleted
	 */
	@Override
	public boolean delete(@Valid TagsEntity tags) {
		boolean isDeleted = false;
		if (tagsRepository.existsById(tags.getTagId())) {
			tagsRepository.delete(tags);
			isDeleted = true;
		}
		return isDeleted;
	}

	public TagsEntity findOrCreateTagByName(String tagName) {
		if (tagsRepository.existsByNameIgnoreCase(tagName)) {
			return tagsRepository.findAllByNameIgnoreCase(tagName).get(0);
		} else {
			return tagsRepository.save(new TagsEntity(tagName));
		}
	}

	/**
	 * Convert a list of TagsEntity objects to a list of Tags objects.
	 * If the list of TagsEntity objects is empty, method throws a
	 * NoSuchElementException.
	 *
	 * @param tagsEntities the list of TagsEntity objects to convert
	 * @return a list of Tags objects
	 * @throws NoSuchElementException if the list of tagsEntities is empty
	 */
	private List<Tags> from(List<TagsEntity> tagsEntities) {
		var tags = tagsEntities.stream()
				.map(te -> new Tags(te))
				.collect(Collectors.toList());
		if (tags.isEmpty()) {
			throw new NoSuchElementException();
		}
		return tags;
	}

}
