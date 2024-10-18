package com.PiXl.mainframe.Models;

public class Posts {
	private String postId;
	private String authorId;
	private String content;
	private String media;
	private Long likesCount;
	private Long commentsCount;
	
	public Posts(String postId, String authorId, String content, String media, Long likesCount, Long commentsCount) {
		super();
		this.postId = postId;
		this.authorId = authorId;
		this.content = content;
		this.media = media;
		this.likesCount = likesCount;
		this.commentsCount = commentsCount;
	}
	
	/**
	 * @return the postId
	 */
	public String getPostId() {
		return postId;
	}
	/**
	 * @param postId the postId to set
	 */
	public void setPostId(String postId) {
		this.postId = postId;
	}
	/**
	 * @return the authorId
	 */
	public String getAuthorId() {
		return authorId;
	}
	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
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
	 * @return the likesCount
	 */
	public Long getLikesCount() {
		return likesCount;
	}
	/**
	 * @param likesCount the likesCount to set
	 */
	public void setLikesCount(Long likesCount) {
		this.likesCount = likesCount;
	}
	/**
	 * @return the commentsCount
	 */
	public Long getCommentsCount() {
		return commentsCount;
	}
	/**
	 * @param commentsCount the commentsCount to set
	 */
	public void setCommentsCount(Long commentsCount) {
		this.commentsCount = commentsCount;
	}
}
