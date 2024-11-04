package com.PiXl.mainframe.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tags")
public class TagsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

	@ManyToMany(mappedBy = "tags")
    private Set<PostsEntity> posts = new HashSet<>();
	
    public TagsEntity(Long id, String name, Set<PostsEntity> posts) {
		super();
		this.tagId = id;
		this.name = name;
		this.posts = posts;
	}

	/**
	 * @return the id
	 */
	public Long getTagId() {
		return tagId;
	}

	/**
	 * @param id the id to set
	 */
	public void setTagId(Long id) {
		this.tagId = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the posts
	 */
	public Set<PostsEntity> getPosts() {
		return posts;
	}

	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<PostsEntity> posts) {
		this.posts = posts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(tagId, name, posts);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagsEntity other = (TagsEntity) obj;
		return Objects.equals(tagId, other.tagId) && Objects.equals(name, other.name) && Objects.equals(posts, other.posts);
	}
    
//	public TagsEntity(Tags tag) {
//		this(tag.getId(), tag.getName());
//	}
}