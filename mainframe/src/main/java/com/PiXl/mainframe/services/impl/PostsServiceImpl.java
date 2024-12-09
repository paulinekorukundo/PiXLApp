package com.PiXl.mainframe.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.PiXl.mainframe.services.TagsService;


@Service
public class PostsServiceImpl implements PostsService {
	
	@Autowired
	private PostsRepository postRepo;
	
	@Autowired
	private TagsRepository tagsRepo;
	
	@Autowired
	private TagsService tagsServ;
	
	private static long offset = 0;
	

	private final Path fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();

    public void PostService() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
	
	@Override
	public List<PostsEntity> getAllPosts() {
		List<PostsEntity> postEntities = postRepo.findAll();
		if(postEntities.isEmpty()) {
			return new ArrayList<PostsEntity>();
		}
		return postEntities;
//		return postEntities.stream().map(this :: of).collect(Collectors.toList());
//		List<Posts> posts= new ArrayList<>();
//		for(PostsEntity p : postEntities) {
//			posts.add(of(p));
//		}
//		return posts;
	}

	@Override
	public Posts getPostById(Long postId) {
		Optional<PostsEntity> postE = postRepo.findById(postId);
		if(postE.isEmpty()) {
			return null;
		}
		return of(postE.get());
		
	}

//    @Override
//    public Posts saveNewPost(MultipartFile file, String userId, String content, String tagName) {
//        try {
//
//            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//            
//            if (fileName.contains("..")) {
//                throw new FileStorageException("Invalid path sequence in filename: " + fileName);
//            }
//            String newFileName = System.currentTimeMillis() + "_" + fileName;
//            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
//            Files.createDirectories(targetLocation.getParent());
//            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
//
//            PostsEntity postEntity = new PostsEntity();
//            postEntity.setUserId(userId);
//            postEntity.setContent(content);
//            postEntity.setMedia(newFileName); 
//
//            if (tagName != null && !tagName.isEmpty()) {
//                TagsEntity tag = tagsRepo.findByName(tagName)
//                    .orElseGet(() -> {
//                        TagsEntity newTag = new TagsEntity();
//                        newTag.setName(tagName);
//                        return tagsRepo.save(newTag);
//                    });
//                postEntity.setTags(tag);
//            }
//
//            PostsEntity savedPostEntity = postRepo.save(postEntity);
//
//            return new Posts(savedPostEntity);
//
//        } catch () {
//            
//        }
//    }
   
	@Override
	public PostsEntity saveNewPost(PostsEntity post, Set<TagsEntity> tags, MultipartFile file) {
		try {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            if (fileName.contains("..")) {
                throw new FileStorageException("Invalid path sequence in filename: " + fileName);
            }
            String newFileName = System.currentTimeMillis() + "_" + fileName;
            Path targetLocation = this.fileStorageLocation.resolve(newFileName);
            Files.createDirectories(targetLocation.getParent());
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            post.setMedia(newFileName);
            
            HashSet<TagsEntity> tagsToSave = new HashSet<>();
            

            if(!tags.isEmpty()) {
            	for(TagsEntity t : tags) {
            		tagsToSave.add(tagsServ.findOrCreateTagByName(t.getName()));
            	}
            	tagsRepo.saveAll(tagsToSave);
    		}
            
    		post.setTagsForPost(tagsToSave);
    		PostsEntity savedPost = postRepo.save(post);
    		return savedPost;

		}catch(IOException ex) {
			throw new FileStorageException("Could not store file " + file.getOriginalFilename() + ". Please try again!", ex);
		}
	}
	
	@Override
	public Posts savePost(Posts post) {
//	    PostsEntity postEntity = of(post);
//	    if (post.getTagName() != null) {
//	        TagsEntity tag = tagsRepo.findByName(post.getTagName())
//	            .orElseGet(() -> {
//	                TagsEntity newTag = new TagsEntity();
//	                newTag.setName(post.getTagName());
//	                return tagsRepo.save(newTag);
//	            });
//	        postEntity.setTags(tag);
//	    }
//	    PostsEntity savedPost = postRepo.save(postEntity);
//	    return of(savedPost);
		return null;
	}

	@Override
	public PostsEntity editExistingPost(PostsEntity post) {
		if(!postRepo.existsById(post.getPostId())) {
			throw new IllegalArgumentException("Post does not exist!");
		}
		PostsEntity savedPost = postRepo.save(post);
		return savedPost;
	}

	@Override
	public List<PostsEntity> getAllPostForUser(String userId) {
		List<PostsEntity> postEntities = postRepo.findByUserId(userId);
		if(postEntities.isEmpty()) {
			return new ArrayList<PostsEntity>();
		}
		return postEntities;
	}

	@Override
	public boolean deletePost(Long postId) {
		boolean isDeletedPost = false;
		
		if(!postRepo.existsById(postId)) {
			throw new IllegalArgumentException("Post does not exist!");
		}else {
			postRepo.deleteById(postId);
			isDeletedPost = true;
		}
		return isDeletedPost;
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
	public List<PostsEntity> getAllPostsWithTag(String tagName) {		TagsEntity tag = tagsRepo.findByNameContainingIgnoreCase(tagName).get(0);
		List<Long> postIds = postRepo.findAllPostIdFromTagId(tag.getTagId());
		return postRepo.findByPostIdIn(postIds);
	}

	@Override
	public List<Posts> searchPosts(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	private String fileExistsInFolder(String fileName) {
        File folder = new File(fileStorageLocation.toString());
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.getName().contains(fileName)) {
                        return file.getName();
                    }
                }
            }
        }
        return null;
    }
	
	@Override
	public List<PostsEntity> savePostsInBatch(List<PostsEntity> posts) {
		List<PostsEntity> postsToSave = new ArrayList<>();
		for(PostsEntity post : posts) {
			// Handle Tags
			HashSet<TagsEntity> tagsToSave = new HashSet<>();
			if(!post.getTagsForPost().isEmpty()) {
	        	for(TagsEntity t : post.getTagsForPost()) {
	        		tagsToSave.add(tagsServ.findOrCreateTagByName(t.getName()));
	        	}
	        	tagsRepo.saveAll(tagsToSave);
			}
			post.setTagsForPost(tagsToSave);
			
			// Handle Media
			post.setMedia(fileExistsInFolder(post.getMedia()));

			postsToSave.add(post);
		}
		List<PostsEntity> savedPosts = new ArrayList<>();
		postRepo.saveAll(postsToSave).forEach(savedPosts :: add);
		return savedPosts;
	}

}
