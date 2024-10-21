package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.Optional;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.services.PostsService;

public class PostsServiceImpl implements PostsService {

	@Override
	public List<PostsEntity> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<PostsEntity> getPostById(Long postId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public PostsEntity saveNewPost(PostsEntity post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostsEntity editExistingPost(PostsEntity post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostsEntity> getAllPostForUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Long postId) {
		// TODO Auto-generated method stub

	}

}
