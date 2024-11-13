package com.PiXl.mainframe.entities;


import com.PiXl.mainframe.models.Posts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
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
    
    @ManyToOne
    @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    private TagsEntity tags; // = new HashSet<>();
   
    
    public PostsEntity() {
	}

	public PostsEntity(Long postId, String user_id, String content, String media, Long likes_count,
			Long comments_count) {
		super();
		this.postId = postId;
		this.userId = user_id;
		this.content = content;
		this.media = media;
		this.likesCount = likes_count;
		this.commentsCount = comments_count;
	}
    
	/**
	 * @return the postId
	 */
	public Long getPostId() {
		return postId;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	/**
	 * @return the user_id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(String user_id) {
		this.userId = user_id;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the media
	 */
	public String getMedia() {
		return media;
	}

	/**
	 * @param media the media to set
	 */
	public void setMedia(String media) {
		this.media = media;
	}

	/**
	 * @return the likes_count
	 */
	public Long getLikesCount() {
		return likesCount;
	}

	/**
	 * @param likes_count the likes_count to set
	 */
	public void setLikesCount(Long likes_count) {
		this.likesCount = likes_count;
	}

	/**
	 * @return the comments_count
	 */
	public Long getCommentsCount() {
		return commentsCount;
	}

	/**
	 * @param comments_count the comments_count to set
	 */
	public void setComments_count(Long comments_count) {
		this.commentsCount = comments_count;
	}
	
	// TODO: Need to fix the tags in the constructor
	public PostsEntity(Posts post) {
		this(post.getPostId(), post.getUserId(),
				post.getContent(), post.getMedia(),
				post.getLikesCount(), post.getCommentsCount());
	}
}
