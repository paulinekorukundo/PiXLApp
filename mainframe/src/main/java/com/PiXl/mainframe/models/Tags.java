package com.PiXl.mainframe.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.PiXl.mainframe.entities.TagsEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Tags {
	private Long tagId;
	private String name;
	private Set<Posts> posts = new HashSet<>();
	
	public Tags(Long id, String name) {
		this.tagId = id;
		this.name = name;
	}
	
//	public Tags(Long id, String name, Set<Posts> posts) {
//		super();
//		this.tagId = id;
//		this.name = name;
//		this.posts = posts;
//	}
//	/**
//	 * @return the id
//	 */
//	public Long getTagId() {
//		return tagId;
//	}
//	/**
//	 * @param id the id to set
//	 */
//	public void setTagId(Long id) {
//		this.tagId = id;
//	}
//	/**
//	 * @return the name of the tag
//	 */
//	public String getName() {
//		return name;
//	}
//	/**
//	 * @param name the name to set
//	 */
//	public void setName(String name) {
//		this.name = name;
//	}
//	/**
//	 * @return the posts
//	 */
//	public Posts getPosts() {
//		return posts;
//	}
//	/**
//	 * @param posts the posts to set
//	 */
//	public void setPosts(Posts posts) {
//		this.posts = posts;
//	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(name, posts, tagId);
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
		return Objects.equals(name, other.name) && Objects.equals(posts, other.posts)
				&& Objects.equals(tagId, other.tagId);
	}

	public Tags(TagsEntity te) {
		this.tagId = te.getTagId();
		this.name = te.getName();
		this.posts = te.getPosts().stream()
				.map(Posts::new)
				.collect(Collectors.toSet());
	}

}
