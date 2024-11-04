package com.PiXl.mainframe.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;


@Entity
@Table(name = "post_tags")
public class PostTagEntity {
	@EmbeddedId
    private PostTagIdEntity id;

    @ManyToOne
    @MapsId("postId")
    @JoinColumn(name = "post_id")
    private PostsEntity post;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private TagsEntity tag;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    public PostTagEntity() {}

    public PostTagEntity(PostsEntity post, TagsEntity tag) {
        this.post = post;
        this.tag = tag;
        this.id = new PostTagIdEntity(post.getPost_id(), tag.getTagId());
        this.createdAt = LocalDateTime.now();
    }
    
}
