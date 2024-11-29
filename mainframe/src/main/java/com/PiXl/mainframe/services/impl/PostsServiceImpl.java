package com.PiXl.mainframe.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.PiXl.mainframe.entities.PostsEntity;
import com.PiXl.mainframe.entities.TagsEntity;
import com.PiXl.mainframe.exceptions.FileStorageException;
import com.PiXl.mainframe.models.Posts;
import com.PiXl.mainframe.repositories.PostsRepository;
import com.PiXl.mainframe.repositories.TagsRepository;
import com.PiXl.mainframe.services.PostsService;


@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postRepo;
	
	@Autowired
	private TagsRepository tagsRepo;
	
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

	private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public void PostService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    @Override
    public Posts saveNewPost(MultipartFile file, String userId, String content, String tagName) {
        try {

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            
            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid path sequence in filename: " + fileName);
            }
            String newFileName = System.currentTimeMillis() + "_" + fileName;
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.createDirectories(targetLocation.getParent());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            PostsEntity postEntity = new PostsEntity();
            postEntity.setUserId(userId);
            postEntity.setContent(content);
            postEntity.setMedia(newFileName); 

            if (tagName != null && !tagName.isEmpty()) {
                TagsEntity tag = tagsRepo.findByName(tagName)
                    .orElseGet(() -> {
                        TagsEntity newTag = new TagsEntity();
                        newTag.setName(tagName);
                        return tagsRepo.save(newTag);
                    });
                postEntity.setTags(tag);
            }

            PostsEntity savedPostEntity = postRepo.save(postEntity);

            return new Posts(savedPostEntity);

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
        }
    }
    
    // PK Comment: Creates post with new tag but no media url
    @Override
	public Posts savePost(Posts post) {
	    PostsEntity postEntity = of(post);

	    if (post.getTagName() != null) {
	        TagsEntity tag = tagsRepo.findByName(post.getTagName())
	            .orElseGet(() -> {

	                TagsEntity newTag = new TagsEntity();
	                newTag.setName(post.getTagName());
	                return tagsRepo.save(newTag);
	            });
	        postEntity.setTags(tag);
	    }

	    PostsEntity savedPost = postRepo.save(postEntity);
	    return of(savedPost);
	}

	@Override
	public Posts editExistingPost(Posts post) {
		if(!postRepo.existsById(post.getPostId())) {
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

	@Override
	public List<Posts> getPostsByIdList(List<Long> ids) {
		if(ids.isEmpty()) {
			return null;
		}
		List<PostsEntity> postEntities = postRepo.findByPostIdIn(ids);
		List<Posts> posts= new ArrayList<>();
		for(PostsEntity p : postEntities) {
			posts.add(of(p));
		}
		return posts;
	}

	// Can we not store User based Liked? Just Likes in total.
	@Override
	public void likePost(Long postId) {
		PostsEntity post = postRepo.findById(postId).get();
		changeLikes(post, 1);
	}

	@Override
	public void unLikePost(Long postId) {
		PostsEntity post = postRepo.findById(postId).get();
		changeLikes(post, -1);
	}
	
	private void changeLikes(PostsEntity post, long changeVal) {
		post.setLikesCount(post.getLikesCount() + changeVal);
		postRepo.save(post);
	}

	@Override
	public List<Posts> getAllPostsWithTag(String tagName) {
//		List<PostsEntity> postEntities = postRepo.findAll();
//		List<PostsEntity> filteredPosts = postEntities.stream()
//			    .filter(post -> post.getTags()
//			        .stream()
//			        .anyMatch(tag -> tag.getName().contains(tagName)))
//			    .collect(Collectors.toList());
//		
//		List<Posts> posts = new ArrayList<>();
//		for(PostsEntity p: filteredPosts) {
//			posts.add(of(p));
//		}
//		
//		return posts;
		return new ArrayList<Posts>();
	}

	@Override
	public List<Posts> searchPosts(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
