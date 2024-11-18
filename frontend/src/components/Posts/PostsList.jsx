import React, { useEffect, useState } from "react";
import { Card, Text, Loader, Grid, Group, Image } from "@mantine/core";
import { fetchAllPosts } from "./api";
import PostActions from "./PostsActions";
import axios from "axios";


function PostsList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadPosts = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/posts/");
        //const response = await fetchAllPosts;
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

  if (loading) return <Loader />;

  return (
    <Grid>
      {posts?.length > 0 ? (
        posts.map((post) => (
          <Grid.Col span={12} key={post.postId}>

            <Card 
              shadow="sm" 
              padding="lg"
              component="a"
              href="https://www.youtube.com/watch?v=dQw4w9WgXcQ"
              target="_blank"
            >
              <Card.Section>
                <Image
                  src="https://images.unsplash.com/photo-1447078806655-40579c2520d6?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  h={160}
                  alt="Food!"
                />
              </Card.Section>
              <Text weight={500}>{post.content}</Text>              
              <Group mt="md" position="apart">
                <PostActions postId={post.postId} likes={post.likesCount} comments={post.commentsCount} />
              </Group>
            </Card>
          </Grid.Col>
        ))
      ) : (
        <Text>No posts available</Text>
      )}
    </Grid>
  );
}

export default PostsList;
