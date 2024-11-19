import axios from "axios";
import React, { useEffect, useState } from "react";
import { Card, Text, Loader, Group, Image } from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";

function PostsList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
 
  useEffect(() => {
    const loadPosts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/posts/");
        console.log("Response: ", response.data);
        setPosts(response.data || []); 
        
    } catch (error) {
        console.error("Error fetching posts:", error);
      } finally {
        setLoading(false);
      }
    };

    loadPosts();
  }, []);

  const likePost = async (postId) => {
    try {
      const response = await axios.post("http://localhost:8080/api/v1/posts/likePost", { postId });
      return response.data;
    } catch (error) {
      console.error("Error liking post:", error);
      throw error;
    }
  };

  const handleLikeUpdate = async (postId, updatedLikes) => {
    try {
      const response = await likePost(postId);
      setPosts((prevPosts) =>
        prevPosts.map((post) =>
          post.postId === postId ? { ...post, likesCount: response.likesCount } : post
        )
      );
      window.location.reload;
    } catch (error) {
      console.error("Error updating likes:", error);
    }
  };


  if (loading) return <Loader />;

  return (
    <Card>
      {posts?.length > 0 ? (
        posts.map((post) => (
            <Card 
            withBorder radius="md" p="md" className={classes.card}
              key={post.postId}
              shadow="sm" 
              padding="lg"
              component="a"
              // href=""
              target="_blank"
            >
              <Card.Section>
                <Image
                  src="https://images.unsplash.com/photo-1447078806655-40579c2520d6?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  // src={post.media} || placeholder_image
                  h={160}
                  alt="Food!"
                />
              </Card.Section>
                <Group mt="md" position="apart">
                  <PostActions 
                    postId={post.postId} 
                    likes={post.likesCount} 
                    comments={post.commentsCount} 
                    onLike={handleLikeUpdate} 
                    />
                </Group>
              <Card.Section className={classes.section}>
                <Group gap={7} mt={5}>
                  <Text fw={500} fz='sm' mt='xs'>{post.userId}</Text>
                  <Text weight={300} fz='sm' mt='xs'>
                    {post.content}   
                     
                    </Text> 
                </Group>
                
              </Card.Section>
            </Card>
        ))
      ) : (
        <Text>No posts available</Text>
      )}
    </Card>
  );
}

export default PostsList;
