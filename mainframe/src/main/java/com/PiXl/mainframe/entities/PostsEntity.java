package com.PiXl.mainframe.entities;


import java.util.HashSet;
import java.util.Set;

import com.PiXl.mainframe.models.Posts;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
public class PostsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "media")
    private String media;

    @Column(name = "likesCount")
    private Long likesCount = 0L;

    @Column(name = "commentsCount")
    private Long commentsCount = 0L;
    

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
//    @JsonBackReference
//    private TagsEntity tags; 
   
    @JsonManagedReference
    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(
    		name = "posts_tags", 
    		joinColumns = @JoinColumn(name = "post_id"), 
    		inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagsEntity> tagsForPost = new HashSet<>();
       
    public PostsEntity() {
	}

	public PostsEntity(String user_id, String content, String media, Long likes_count,
			Long comments_count) {
		super();
//		this.postId = postId;
		this.userId = user_id;
		this.content = content;
		this.media = media;
		this.likesCount = likes_count;
		this.commentsCount = comments_count;
	}
    
	/**
	 * All Arguments Constructor to include Tags
	 * @param postId
	 * @param userId
	 * @param content
	 * @param media
	 * @param likesCount
	 * @param commentsCount
	 * @param tags
	 */
	public PostsEntity(String userId, String content, String media, Long likesCount, Long commentsCount,
			TagsEntity tags) {
		super();
//		this.postId = postId;
		this.userId = userId;
		this.content = content;
		this.media = media;
		this.likesCount = likesCount;
		this.commentsCount = commentsCount;
		this.tagsForPost.add(tags);
	}
	
	public void addTagToPost(TagsEntity tag) {
		this.tagsForPost.add(tag);
	}
	
	
	/**
	 * Converts a posts objects into a posts entity
	 * @param post
	 */
	public PostsEntity(Posts post) {
        this(post.getUserId(),
             post.getContent(), 
             post.getMedia(),
             post.getLikesCount(), 
             post.getCommentsCount());

    }
	
}
