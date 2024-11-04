package com.PiXl.mainframe.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PostTagIdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long postId;
    private Long tagId;
	
    public PostTagIdEntity(Long postId, Long tagId) {
		super();
		this.postId = postId;
		this.tagId = tagId;
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
	 * @return the tagId
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(postId, tagId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostTagIdEntity other = (PostTagIdEntity) obj;
		return Objects.equals(postId, other.postId) && Objects.equals(tagId, other.tagId);
	}
}
