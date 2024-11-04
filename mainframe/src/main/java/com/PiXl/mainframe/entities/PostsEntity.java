package com.PiXl.mainframe.entities;

import java.util.HashSet;
import java.util.Set;

import com.PiXl.mainframe.models.Posts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "posts")
public class PostsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long post_id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "content", length = 2000)
    private String content;

    @Column(name = "media")
    private String media;

    @Column(name = "likes_count")
    private Long likes_count = 0L;

    @Column(name = "comments_count")
    private Long comments_count = 0L;
    
    @ManyToMany
    @JoinTable(
        name = "post_tags",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagsEntity> tags = new HashSet<>();
    
    
    public PostsEntity() {
	}

	public PostsEntity(Long postId, String user_id, String content, String media, Long likes_count,
			Long comments_count, Set<TagsEntity> tags) {
		super();
		this.post_id = postId;
		this.user_id = user_id;
		this.content = content;
		this.media = media;
		this.likes_count = likes_count;
		this.comments_count = comments_count;
		this.tags = tags;
	}
    
	/**
	 * @return the postId
	 */
	public Long getPost_id() {
		return post_id;
	}

	/**
	 * @param postId the postId to set
	 */
	public void setPost_id(Long postId) {
		this.post_id = postId;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
	public Long getLikes_count() {
		return likes_count;
	}

	/**
	 * @param likes_count the likes_count to set
	 */
	public void setLikes_count(Long likes_count) {
		this.likes_count = likes_count;
	}

	/**
	 * @return the comments_count
	 */
	public Long getComments_count() {
		return comments_count;
	}

	/**
	 * @param comments_count the comments_count to set
	 */
	public void setComments_count(Long comments_count) {
		this.comments_count = comments_count;
	}
	
	/**
	 * @return the tags
	 */
	public Set<TagsEntity> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<TagsEntity> tags) {
		this.tags = tags;
	}
	
	public long incrementLikes() {
		this.setLikes_count(this.getLikes_count() + 1);
		return this.getLikes_count();
	}

	public long decrementLikes() {
		this.setLikes_count(this.getLikes_count() - 1);
		return this.getLikes_count();
	}
	
	// TODO: Need to fix the tags in the constructor
	public PostsEntity(Posts post) {
		this(post.getPost_id(), post.getUser_id(),
				post.getContent(), post.getMedia(),
				post.getLikes_count(), post.getComments_count(), null);
	}
}
