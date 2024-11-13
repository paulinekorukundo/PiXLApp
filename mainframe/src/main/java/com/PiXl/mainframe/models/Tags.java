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
