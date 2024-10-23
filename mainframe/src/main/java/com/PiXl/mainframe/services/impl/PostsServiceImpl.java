package com.PiXl.mainframe.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.models.Posts;
import com.PiXl.mainframe.repositories.PostsRepository;
import com.PiXl.mainframe.services.PostsService;


@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postRepo;
	
	@Override
	public List<Posts> getAllPosts() {
		List<PostsEntity> postEntities = postRepo.findAll();
		if(postEntities.isEmpty()) {
			return new ArrayList<Posts>();
		}
		List<Posts> posts= new ArrayList<>();
		for(PostsEntity p : postEntities) {
			posts.add(of(p));
		}
		return posts;
	}

	@Override
	public Posts getPostById(Long postId) {
		Optional<PostsEntity> postE = postRepo.findById(postId);
		if(postE.isEmpty()) {
			return null;
		}
		return of(postE.get());
		
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
	public List<Posts> getAllPostForUser(String userId) {
		List<PostsEntity> postEntities = postRepo.findByUserId(userId);
		if(postEntities.isEmpty()) {
			return new ArrayList<Posts>();
		}
		List<Posts> posts= new ArrayList<>();
		for(PostsEntity p : postEntities) {
			posts.add(of(p));
		}
		return posts;
	}

	@Override
	public void deletePost(Long postId) {
		// TODO Auto-generated method stub

	}
	
	private Posts of(PostsEntity post) {
		return new Posts(post);
	}

}
