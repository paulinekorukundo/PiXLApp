import axios from "axios";
import React, { useEffect, useState } from "react";
import { Card, Text, Loader, Group, Image, Button, ActionIcon, Modal, TextInput, FileInput } from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";
import { IconImageInPicture, IconPlus } from "@tabler/icons-react";
import { useDisclosure } from "@mantine/hooks";
import "../../assets/General.css";
import { useAppContext } from "../../context/AppContext";
import { API_URL } from "../../config";  

function PostsList() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});

  // User
  const { userDetails } = useAppContext();

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState("");

  const icon = <IconImageInPicture className="image-icon" stroke={1.5} />;

  const [message, setMessage] = useState("");

  const handleSubmit = async () => {
    try {
      const formData = new FormData();
      formData.append("media", media);
      formData.append("userId", userDetails.email);
      formData.append("content", content);
      if (tag) formData.append("tag", tag);

      await axios.post(
        "http://localhost:8080/api/v1/posts/",
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
          },
        }
      );
      setMessage("Post created successfully!");
      close();
    } catch (error) {
      console.error("Error saving post:", error);
      if (error.response) {
        setMessage(`Error: ${error.response.data.message}`);
      } else {
        setMessage("An unexpected error occurred");
      }
    }
  };

  
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
      setLoadedImages(loadedImages.reduce((acc, img, index) => ({ ...acc, [posts[index].postId]: img }), {}));
    };

    loadImages();
  }, [posts, API_URL]);

  //View posts
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
    <>
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
                  // src="https://images.unsplash.com/photo-1447078806655-40579c2520d6?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                  src={post.media ? `${API_URL}/media/${post.media}` : '/coming-soon.png'}
                  alt={`Post image for ${post.content}`}
                  h={160}
                  onError={(e) => {
                    e.target.src = "/coming-soon.png"
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

    {/* <Button fullWidth radius="md" mt="sm" size="md" variant="default"
    onClick={open}
    >
      Create Post
    </Button> */}
    <ActionIcon size={42} 
      variant="default" 
      aria-label="Add Post"
      onClick={open}
      >
        
      <IconPlus 
      className={classes.like} stroke={1.5} 
       />
    </ActionIcon>
    

    <Modal opened={opened} onClose={close} title="Add Post">
      <TextInput
        disabled
        label="Username"
        value={userDetails.email}
      />
      <TextInput
        label="Content"
        placeholder="Post Description"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <FileInput
        clearable
        rightSection={icon}
        label="Upload Media"
        placeholder="Add Media"
        accept="image/png,image/jpeg"
        onChange={(media) => setMedia(media)} 
      />
      <TextInput
        label="Tag"
        placeholder="Add Tag to Post"
        value={tag}
        onChange={(e) => setTag(e.target.value)}
      />
      <Group position="right" mt="md">
        <Button onClick={handleSubmit}>Save</Button>
      </Group>
    </Modal>
    </>
  );
}

export default PostsList;
