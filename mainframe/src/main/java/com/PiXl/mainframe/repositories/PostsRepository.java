package com.PiXl.mainframe.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.models.Posts;

public interface PostsRepository extends CrudRepository<PostsEntity, Long> {
	List<PostsEntity> findAll();
	List<Posts> findByUserId(String UserId);
	@SuppressWarnings("unchecked")
	PostsEntity save(PostsEntity post);
	void deleteById(Long postId);
    boolean existsById(Long postId);
    long count();
}
