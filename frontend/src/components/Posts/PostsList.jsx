import axios from "axios";
import React, { useEffect, useState } from "react";
import {
  Card,
  Text,
  Loader,
  Group,
  Image,
  Grid,
  Box,
  TextInput,
  Flex,
} from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { API_URL } from "../../config";
import debounce from "lodash/debounce"; // Add this for debouncing input

function PostsList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});
  const [searchTag, setSearchTag] = useState(""); // State for tag search

  //Get Images
  useEffect(() => {
    const loadImages = async () => {
      if (posts.length === 0) return;

      const imagePromises = posts.map(async (post) => {
        try {
          const response = await axios.get(`${API_URL}/media/${post.media}`);
          return response.data;
        } catch (error) {
          console.error(`Error loading image for post ${post.postId}:`, error);
          return null;
        }
      });

      const loadedImages = await Promise.all(imagePromises);
      setLoadedImages(
        loadedImages.reduce(
          (acc, img, index) => ({ ...acc, [posts[index].postId]: img }),
          {}
        )
      );
    };

    loadImages();
  }, [posts, API_URL]);

  const loadPosts = async (searchTag = "") => {
    // setLoading(true);
    try {
      const response = searchTag
        ? await axios.post(
            "http://localhost:8080/api/v1/posts/findPostsByTag",
            {
              tagName: searchTag,
            }
          )
        : await axios.get("http://localhost:8080/api/v1/posts/");

      setPosts(response.data || []);
    } catch (error) {
      console.error("Error fetching posts:", error);
    } finally {
      setLoading(false);
    }
  };

  // Debounced search handler
  const debouncedSearch = debounce((value) => {
    if (value.length >= 3) loadPosts(value);
    else loadPosts();
  }, 300);

  const handleSearchChange = (e) => {
    const value = e.target.value;
    setSearchTag(value);
    debouncedSearch(value);
  };

  const likePost = async (postId) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/api/v1/posts/likePost",
        { postId }
      );
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
          post.postId === postId
            ? { ...post, likesCount: response.likesCount }
            : post
        )
      );
      window.location.reload;
    } catch (error) {
      console.error("Error updating likes:", error);
    }
  };

  // Initial load
  useEffect(() => {
    loadPosts();
  }, []);

  if (loading) return <Loader />;

  return (
    <>
      <Flex
        align="center"
        justify="space-between"
        mb="md"
        p="sm"
        sx={(theme) => ({
          backgroundColor: theme.colors.gray[1],
          borderRadius: theme.radius.sm,
          boxShadow: theme.shadows.xs,
        })}
      >
        <Box sx={{ flex: 1, marginRight: "1rem" }}>
          <TextInput
            placeholder="Search By Tag"
            value={searchTag}
            onChange={handleSearchChange}
            styles={{
              input: {
                border: "none",
                borderBottom: "1px solid #ced4da",
                borderRadius: 0,
                padding: "10px 0",
                fontSize: "16px",
                width: "100%",
              },
            }}
          />
        </Box>
      </Flex>
      <Grid gap={10}>
        {posts?.length > 0 ? (
          posts.map((post) => (
            <Grid.Col span={{ base: 12, md: 6, lg: 4 }} key={post.postId}>
              <Card
                withBorder
                radius="md"
                p="md"
                className={classes.card}
                shadow="sm"
                component="a"
                // href=""
                target="_blank"
              >
                <Card.Section>
                  <Image
                    src={
                      post.media
                        ? `${API_URL}/media/${post.media}`
                        : "/coming-soon.png"
                    }
                    alt={`Post image for ${post.content}`}
                    h={300}
                    onError={(e) => {
                      e.target.src = "/coming-soon.png";
                    }}
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
                    <Text fw={500} fz="sm" mt="xs">
                      {post.userId}
                    </Text>
                    <Text weight={300} fz="sm" mt="xs">
                      {post.content}
                    </Text>
                  </Group>
                  {/* Render tags here */}
                  {post.tagsForPost?.length > 0 && (
                    <Group gap={5} mt="sm">
                      {post.tagsForPost.map((tag, index) => (
                        <Text
                          key={index}
                          size="xs"
                          color="blue"
                          sx={(theme) => ({
                            backgroundColor: theme.colors.blue[0],
                            padding: "2px 8px",
                            borderRadius: theme.radius.xs,
                          })}
                        >
                          #{tag.name}
                        </Text>
                      ))}
                    </Group>
                  )}
                </Card.Section>
              </Card>
            </Grid.Col>
          ))
        ) : (
          <Text>No posts available</Text>
        )}
      </Grid>
    </>
  );
}

export default PostsList;

{
  /* <Flex
        align="center"
        justify="space-between"
        mb="md"
        p="sm"
        sx={(theme) => ({
          backgroundColor: theme.colors.gray[1],
          borderRadius: theme.radius.sm,
          boxShadow: theme.shadows.xs,
        })}
      >
        <Box sx={{ flex: 1, marginRight: "1rem" }}>
          <TextInput
            placeholder="Search By Tag"
            value={filterConditions}
            onChange={handleSearchChange}
            styles={{
              input: {
                border: "none",
                borderBottom: "1px solid #ced4da",
                borderRadius: 0,
                padding: "10px 0",
                fontSize: "16px",
                width: "100%",
              },
            }}
          />
        </Box>
      </Flex> */
}
