package com.PiXl.mainframe.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.PiXl.mainframe.models.Tags;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tag")
@NoArgsConstructor
@Setter
@Getter
public class TagsEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

	/**
     * The name of the tag.
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Set of posts associated with this tag.
     */
    @OneToMany(mappedBy = "tags")
    private Set<PostsEntity> posts = new HashSet<>();
	
    /**
     * Constructs a new TagsEntity with the given id and name.
     *
     * @param id The ID of the tag.
     * @param name The name of the tag.
     */
	public TagsEntity(Long id, String name) {
		super();
		this.tagId = id;
		this.name = name;
	}
	
	/**
     * Constructs a new TagsEntity with the given id, name, and associated posts.
     *
     * @param id The ID of the tag.
     * @param name The name of the tag.
     * @param postsEntity Set of posts associated with this tag.
     */
    public TagsEntity(Long id, String name, Set<PostsEntity> postsEntity) {
		super();
		this.tagId = id;
		this.name = name;
		this.posts = postsEntity;
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
    
	 /**
     * Constructs a new TagsEntity from an existing Tags object.
     *
     * @param tag The Tags object to copy from.
     */
	public TagsEntity(Tags tag) {
		this.tagId = tag.getTagId();
		this.name = tag.getName();
		this.posts = tag.getPosts().stream()
				.map(PostsEntity::new)
				.collect(Collectors.toSet());
	}
}