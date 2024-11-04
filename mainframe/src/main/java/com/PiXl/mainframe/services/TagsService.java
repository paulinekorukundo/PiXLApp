package com.PiXl.mainframe.services;

import java.util.List;

import com.PiXl.mainframe.models.Tags;

public interface TagsService {
	List<Tags> getAllTags();
	List<Tags> getAllTagsForPost();
}
