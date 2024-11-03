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
	
	private static long offset = 0;
	
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
	public Posts saveNewPost(Posts post) {
		PostsEntity savedPost = postRepo.save(of(post));
		return of(savedPost);
	}

	@Override
	public Posts editExistingPost(Posts post) {
		if(!postRepo.existsById(post.getPost_id())) {
			throw new IllegalArgumentException("Post does not exist!");
		}
		PostsEntity savedPost = postRepo.save(of(post));
		return of(savedPost);
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
		if(!postRepo.existsById(postId)) {
			throw new IllegalArgumentException("Post does not exist!");
		}
		postRepo.deleteById(postId);
	}
	
	private Posts of(PostsEntity post) {
		return new Posts(post);
	}
	
	private PostsEntity of(Posts post) {
		return new PostsEntity(post);
	}

	@Override
	public List<Posts> getPostByMostLikes(long numOfPosts) {
		if(numOfPosts < 0) {
			throw new IllegalArgumentException("Value cannot be less than 0!");
		}
		List<PostsEntity> postEntities = postRepo.getTopNLikedPosts(numOfPosts, offset);
		List<Posts> posts= new ArrayList<>();
		for(PostsEntity p : postEntities) {
			posts.add(of(p));
		}
		offset = offset + numOfPosts;
		return posts;
	}

}
