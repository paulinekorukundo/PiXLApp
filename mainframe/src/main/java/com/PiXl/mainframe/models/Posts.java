package com.PiXl.mainframe.models;

import java.util.Objects;

import com.PiXl.mainframe.entities.PostsEntity;

public class Posts {
	private Long post_id;
	private String user_id;
	private String content;
	private String media;
	private Long likes_count;
	private Long comments_count;
	
	public Posts(Long post_id, String user_id, String content, String media, Long likes_count, Long comments_count) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.content = content;
		this.media = media;
		this.likes_count = likes_count;
		this.comments_count = comments_count;
	}
	
	/**
	 * @return the post_id
	 */
	public Long getPost_id() {
		return post_id;
	}
	/**
	 * @param post_id the post_id to set
	 */
	public void setPost_id(Long post_id) {
		this.post_id = post_id;
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

	@Override
	public String toString() {
		return "Posts [post_id=" + post_id + ", user_id=" + user_id + ", content=" + content + ", media=" + media
				+ ", likes_count=" + likes_count + ", comments_count=" + comments_count + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(user_id, comments_count, content, likes_count, media, post_id);
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
		return Objects.equals(user_id, other.user_id) && Objects.equals(post_id, other.post_id);
	}
	
	public Posts(PostsEntity post) {
		this(post.getPost_id(), post.getUser_id(),
				post.getContent(), post.getMedia(),
				post.getLikes_count(), post.getComments_count());
	}

}
