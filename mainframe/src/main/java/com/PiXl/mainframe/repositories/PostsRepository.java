package com.PiXl.mainframe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.PiXl.mainframe.entities.PostsEntity;

public interface PostsRepository extends CrudRepository<PostsEntity, Long> {
	List<PostsEntity> findAll();
	Optional<PostsEntity> findById(Long postId);
	
	@Query("SELECT p FROM PostsEntity p WHERE p.user_id = :userId")
	List<PostsEntity> findByUserId(@Param("userId") String userId);
	
	@SuppressWarnings("unchecked")
	PostsEntity save(PostsEntity post);
	void deleteById(Long postId);
    boolean existsById(Long postId);
    long count();
}
