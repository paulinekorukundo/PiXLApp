package com.PiXl.mainframe.models;

import java.util.Objects;
import java.util.Set;

import com.PiXl.mainframe.entities.PostsEntity;

public class Posts {
	private Long postId;
	private String userId;
	private String content;
	private String media;
	private Long likesCount;
	private Long commentsCount;
	private Set<Tags> tags;
	
	public Posts(Long post_id, String user_id, String content, String media, 
			Long likes_count, Long comments_count, Set<Tags> tags) {
		super();
		this.postId = post_id;
		this.userId = user_id;
		this.content = content;
		this.media = media;
		this.likesCount = likes_count;
		this.commentsCount = comments_count;
		this.setTags(tags);
	}
	
	/**
	 * @return the post_id
	 */
	public Long getPostId() {
		return postId;
	}
	/**
	 * @param post_id the post_id to set
	 */
	public void setPostId(Long post_id) {
		this.postId = post_id;
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
	public void setCommentsCount(Long comments_count) {
		this.commentsCount = comments_count;
	}
	
	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Posts [post_id=" + postId + ", user_id=" + userId + ", content=" + content + ", media=" + media
				+ ", likes_count=" + likesCount + ", comments_count=" + commentsCount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(userId, commentsCount, content, likesCount, media, postId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posts other = (Posts) obj;
		return Objects.equals(userId, other.userId) && Objects.equals(postId, other.postId);
	}
	
	public Posts(PostsEntity post) {
		this(post.getPostId(), post.getUserId(),
				post.getContent(), post.getMedia(),
				post.getLikesCount(), post.getCommentsCount(), null);
	}

}
