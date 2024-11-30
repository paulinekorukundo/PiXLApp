package com.PiXl.mainframe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.PiXl.mainframe.entities.PostsEntity;

public interface PostsRepository extends CrudRepository<PostsEntity, Long> {
	List<PostsEntity> findAll();
	List<PostsEntity> findByPostIdIn(List<Long> postIDsList);
	Optional<PostsEntity> findById(Long postId);
	
	@Query("SELECT p FROM PostsEntity p WHERE p.userId = :userId")
	List<PostsEntity> findByUserId(@Param("userId") String userId);
	
	@SuppressWarnings("unchecked")
	PostsEntity save(PostsEntity post);
	void deleteById(Long postId);
    boolean existsById(Long postId);
    long count();
    
    @Query("SELECT p FROM PostsEntity p ORDER BY p.likesCount DESC LIMIT :maxNum OFFSET :off")
    List<PostsEntity> getTopNLikedPosts(@Param("maxNum") Long maxNum, @Param("off") Long off);
 
    
    @Query(value = "SELECT post_id from posts_tags WHERE tag_id = :tagId", nativeQuery = true)
    List<Long> findAllPostIdFromTagId(Long tagId);
}
