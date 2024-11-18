package com.PiXl.mainframe.models;

import java.util.Objects;

import com.PiXl.mainframe.entities.PostsEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posts {
	private Long postId;
	private String userId;
	private String content;
	private String media;
	private Long likesCount;
	private Long commentsCount;
	private String tagName;
	
	public Posts(Long post_id, String user_id, String content, String media, 
			Long likes_count, Long comments_count, String tagName) {
		super();
		this.postId = post_id;
		this.userId = user_id;
		this.content = content;
		this.media = media;
		this.likesCount = likes_count;
		this.commentsCount = comments_count;
//		this.setTags(tags);
		this.tagName = tagName;
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
	
	/**
	 * Converts PostsEntity into a Posts object
	 * @param post entity to convert
	 */
	public Posts(PostsEntity post) {
		this.postId = post.getPostId();
		this.userId = post.getUserId();
		this.content = post.getContent();
		this.media = post.getMedia();
		this.likesCount = post.getLikesCount();
		this.commentsCount = post.getCommentsCount();
		this.tagName = post.getTags().getName();
	}

}
