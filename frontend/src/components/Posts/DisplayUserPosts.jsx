/* eslint-disable react/prop-types */
import axios from "axios";
import { useEffect, useState } from "react";
import { Card, Text, Loader, Group, Image, Grid, Button } from "@mantine/core";
import PostActions from "./PostsActions";
import classes from "../../assets/BadgeCard.module.css";
import "../../assets/General.css";
import { useAppContext } from "../../context/AppContext";
import { API_URL } from "../../config";
import PostModal from "./PostRecipeModal";
import {
  CardContent,
  Typography,
  Chip,
  Grid2,
  CardHeader,
  Divider,
} from "@mui/material";


/**
 * DisplayUserPosts Component
 *
 * This component fetches and displays all posts created by the currently logged-in user.
 * It shows a grid of post cards, each containing an image, textual content, and post actions
 * (like/comment). If no posts are found, it displays a "No posts available" message.
 *
 * @component
 *
 * @prop {boolean} [reload] - A prop that, when changed, triggers the component to refetch the user's posts.
 *
 * @example
 * // Example usage:
 * import { useState } from 'react';
 * import DisplayUserPosts from './DisplayUserPosts';
 *
 * function UserProfile() {
 *   const [reloadFlag, setReloadFlag] = useState(false);
 *
 *   const reloadPosts = () => setReloadFlag(prev => !prev);
 *
 *   return (
 *     <div>
 *       <button onClick={reloadPosts}>Reload User Posts</button>
 *       <DisplayUserPosts reload={reloadFlag} />
 *     </div>
 *   );
 * }
 *
 * @returns {JSX.Element} A grid of user posts or a loading indicator or a message if no posts are available.
 *
 * Internal Details:
 * - **State Variables:**
 *   - `loading` (boolean): Indicates whether the component is currently loading data.
 *   - `loadedImages` (Object): Maps indices to loaded images (currently not heavily utilized).
 *   - `userPosts` (Array|Object): The list of posts fetched for the user. Usually an array of post objects.
 *
 * - **Data Fetching:**
 *   Uses the currently logged-in user's email from the AppContext to fetch their posts from the backend.
 *   If no posts are found, sets `userPosts` to an empty array.
 *
 * - **Image Loading:**
 *   Attempts to load each post's media from the server. If any fails, a placeholder image is used.
 *
 * - **Dependencies:**
 *   - `axios` for HTTP requests.
 *   - `@mantine/core` for UI components like Card, Grid, Loader, Text, and Image.
 *   - `useAppContext` to get the current user's details.
 *
 * - **Lifecycle:**
 *   - On mount and whenever `userDetails.email` or `props.reload` changes, it refetches the user’s posts.
 *   - On `userPosts` change, attempts to load the associated images.
 *
 * @see PostActions for actions available on each post (like, unlike, edit, delete).
 */


function DisplayUserPosts(props) {
  const [opened, setOpened] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [selectedPostId, setSelectedPostId] = useState(null);
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState(null);
  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});
  const [userPosts, setUserPosts] = useState({});
  const [userRecipes, setUserRecipes] = useState([]);
  const [editingPost, setEditingPost] = useState(null); // Track the post being edited
  const { userDetails } = useAppContext();

  //View posts
  useEffect(() => {
    const loadPosts = async () => {
      const userId = userDetails.email;
      try {
        const response = await axios.get(`${API_URL}/api/v1/posts/${userId}`);
        setUserPosts(response.data.data || []);
      } catch (error) {
        if (error.response && error.response.status === 404) {
          setUserPosts([]);
          console.log("No Posts for User");
        } else {
          console.error("Error fetching posts:", error);
        }
      } finally {
        setLoading(false);
      }
    };

    loadPosts();
  }, [userDetails.email, props.reload]);

  //View Recipes
  useEffect(() => {
    const loadRecipes = async () => {
      const userId = userDetails.profileId;
      try {
        const response = await axios.get(
          `${API_URL}/api/v1/recipes/profile/${userId}`
        );
        console.log(response);
        setUserRecipes(response.data || []);
      } catch (error) {
        if (error.response && error.response.status === 404) {
          setUserRecipes([]);
          console.log("No Recipes for User");
        } else {
          console.error("Error fetching posts:", error);
        }
      } finally {
        setLoading(false);
      }
    };

    loadRecipes();
  }, [userDetails.email, props.reload]);

  useEffect(() => {
    const loadImages = async () => {
      if (!Array.isArray(userPosts) || userPosts.length === 0) return;

      try {
        let imagePromises;
        if (Array.isArray(userPosts)) {
          imagePromises = userPosts.map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error
              );
              return null;
            }
          });
        } else if (typeof userPosts === "object") {
          imagePromises = Object.values(userPosts).map(async (post) => {
            try {
              const response = await axios.get(
                `${API_URL}/api/v1/posts/media/${post.media}`
              );
              return response.data;
            } catch (error) {
              console.error(
                `Error loading image for post ${post.postId}:`,
                error
              );
              return null;
            }
          });
        }

        const loadedImages = await Promise.all(imagePromises);
        setLoadedImages(
          loadedImages.reduce(
            (acc, img, index) => ({ ...acc, [index]: img }),
            {}
          )
        );
        setLoading(false);
      } catch (error) {
        console.error("Error loading images:", error);
        setLoading(false);
      }
    };

    loadImages();
  }, [userPosts]);

  const handleOpenCreateModal = () => {
    setEditMode(false);
    setContent("");
    setTag("");
    setMedia(null);
    setOpened(true);
  };

  const handleOpenEditModal = (post) => {
    setEditMode(true);
    setSelectedPostId(post.postId);
    setContent(post.content);
    setTag(post.tag || "");
    setMedia(null); // Assume media is not directly editable
    setOpened(true);
  };

  // Handle edit button click
  const handleEditClick = (post) => {
    setEditingPost(post); // Set the current post in edit mode
    props.onEditPost(post); // Pass the post to the parent component or form
  };

  // Cancel edit
  const handleCancelEdit = () => {
    setEditingPost(null);
    props.onEditPost(null); // Clear the form in parent component
  };

  const chipStyles = (condition) => ({
    backgroundColor: condition ? "#000411" : "#efcb68",
    color: "#fff",
  });
  if (loading) return <Loader />;

  return (
    <>
      <div>
        {/* User Posts Section */}
        <Text fz="xl" fw={700} mb="md">
          Posts
        </Text>
        <Grid gap={10}>
          {userPosts && userPosts?.length > 0 ? (
            userPosts.map((post) => (
              <Grid.Col span={{ base: 12, md: 6, lg: 4 }} key={post.postId}>
                <Card
                  withBorder
                  radius="md"
                  p="md"
                  className={classes.card}
                  key={post.postId}
                  shadow="sm"
                  padding="lg"
                >
                  <Card.Section>
                    <Image
                      src={
                        post.media
                          ? `${API_URL}/api/v1/posts/media/${post.media}`
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
                    />
                    <Button
                      variant="light"
                      size="xs"
                      onClick={() => handleEdit(post, "post")}
                    >
                      Edit
                    </Button>
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
                  </Card.Section>
                </Card>
              </Grid.Col>
            ))
          ) : (
            <Text>No posts available</Text>
          )}
        </Grid>

        {/* User Recipes Section */}
        <Text fz="xl" fw={700} mt="lg" mb="md">
          Recipes
        </Text>
        <Grid gap={10}>
          {userRecipes && userRecipes?.length > 0 ? (
            userRecipes.map((recipe) => (
              <Grid.Col span={{ base: 12, md: 6, lg: 4 }} key={recipe.recipeId}>
                <Card
                  withBorder
                  radius="md"
                  p="md"
                  className={classes.card}
                  key={recipe.recipeId}
                  shadow="sm"
                  padding="lg"
                >
                  <CardHeader
                    title={`${recipe.recipeName}`}
                    sx={{
                      color: "#000411",
                    }}
                  />
                  <CardContent>
                    <Typography variant="body2" color="#160c28" gutterBottom>
                      <strong>Cusine Type:</strong> {recipe.cusineType}
                    </Typography>
                    <Typography variant="body2" color="#160c28" gutterBottom>
                      <strong>Ingredients:</strong> {recipe.recipeIngredients}
                    </Typography>
                    <Typography variant="body2" color="#160c28" gutterBottom>
                      <strong>Instructions:</strong> {recipe.recipeInstructions}
                    </Typography>
                    <Typography variant="body2" color="#160c28">
                      <strong>Prep Time:</strong> {recipe.prepTime} minutes
                    </Typography>
                    <Divider sx={{ backgroundColor: "#8ea8c3", marginY: 2 }} />
                    <Grid2 container spacing={1}>
                      <Grid2 item>
                        <Chip
                          label={`Vegan: ${recipe.isVegan ? "Yes" : "No"}`}
                          sx={chipStyles(recipe.isVegan)}
                        />
                      </Grid2>
                      <Grid2 item>
                        <Chip
                          label={`Vegetarian: ${
                            recipe.isVegetarian ? "Yes" : "No"
                          }`}
                          sx={chipStyles(recipe.isVegetarian)}
                        />
                      </Grid2>
                      <Grid2 item>
                        <Chip
                          label={`Lactose-Free: ${
                            recipe.isLactoseFree ? "Yes" : "No"
                          }`}
                          sx={chipStyles(recipe.isLactoseFree)}
                        />
                      </Grid2>
                      <Grid2 item>
                        <Chip
                          label={`Gluten-Free: ${
                            recipe.isGlutenFree ? "Yes" : "No"
                          }`}
                          sx={chipStyles(recipe.isGlutenFree)}
                        />
                      </Grid2>
                    </Grid2>
                  </CardContent>
                  <Button
                    variant="light"
                    size="xs"
                    onClick={() => handleEdit(recipe, "recipe")}
                  >
                    Edit
                  </Button>
                </Card>
              </Grid.Col>
            ))
          ) : (
            <Text>No recipes available</Text>
          )}
        </Grid>
      </div>
      <PostModal
        opened={opened}
        onClose={() => setOpened(false)}
        title={editMode ? "Edit Post" : "Add Post"}
        content={content}
        tag={tag}
        media={media}
        setContent={setContent}
        setTag={setTag}
        setMedia={setMedia}
        // onSubmit={handleSubmit}
      />
    </>
  );
}

export default DisplayUserPosts;
