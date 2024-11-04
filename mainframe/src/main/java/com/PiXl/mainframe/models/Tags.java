package com.PiXl.mainframe.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Tags {
	private Long id;
	private String name;
	private Set<Posts> posts = new HashSet<>();
	
	public Tags(Long id, String name, Set<Posts> posts) {
		super();
		this.id = id;
		this.name = name;
		this.posts = posts;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	public Set<Posts> getPosts() {
		return posts;
	}
	/**
	 * @param posts the posts to set
	 */
	public void setPosts(Set<Posts> posts) {
		this.posts = posts;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, name, posts);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tags other = (Tags) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Objects.equals(posts, other.posts);
	}
	
	
//	public Tags(TagsEntity tag) {
//		this(tag.getId(), tag.getName(), tag.getPosts());
//	}

}
