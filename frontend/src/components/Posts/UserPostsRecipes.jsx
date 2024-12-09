import React, { useEffect, useState } from "react";
import {
  Grid,
  Card,
  Group,
  Text,
  Image,
  ActionIcon,
} from "@mantine/core";
import { IconEdit, IconX } from "@tabler/icons-react";
import axios from "axios";
import { useAppContext } from "../../context/AppContext";
import PostRecipeModal from "./PostRecipeModal";
import classes from "../../assets/BadgeCard.module.css";
import PostActions from "./PostsActions";
import {
  CardContent,
  Typography,
  Chip,
  Grid2,
  CardHeader,
  Divider,
} from "@mui/material";

import { API_URL } from "../../config";
import { notifications } from "@mantine/notifications";

const UserPostsRecipes = (props) => {
  const { userDetails } = useAppContext();
  const [posts, setPosts] = useState([]);
  const [recipes, setRecipes] = useState([]);
  const [opened, setOpened] = useState(false);
  const [editMode, setEditMode] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);
  const [type, setType] = useState("post"); // Either "post" or "recipe"
  const [content, setContent] = useState("");
  const [tag, setTag] = useState("");
  const [media, setMedia] = useState(null);
  const [recipeName, setRecipeName] = useState("");
  const [recipeIngredients, setRecipeIngredients] = useState("");
  const [recipeInstructions, setRecipeInstructions] = useState("");
  const [cusineType, setCusineType] = useState("");
  const [isVegan, setIsVegan] = useState(false);
  const [isVegetarian, setIsVegetarian] = useState(false);
  const [isLactoseFree, setIsLactoseFree] = useState(false);
  const [isGlutenFree, setIsGlutenFree] = useState(false);
  const [profileId, setProfileId] = useState(userDetails?.profileId || 0);
  const [prepTime, setPrepTime] = useState(0);
  const [recipeId, setRecipeId] = useState(0);
  const [postId, setPostId] = useState(0);
  const [message, setMessage] = useState("");

  const [loading, setLoading] = useState(true);
  const [loadedImages, setLoadedImages] = useState({});

  // Fetch data
  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId = userDetails.email;
        const profileId = userDetails.profileId;
        const postsResponse = await axios.get(
          `${import.meta.env.VITE_API_URL}/api/v1/posts/${userId}`
        );
        const recipesResponse = await axios.get(
          `${import.meta.env.VITE_API_URL}/api/v1/recipes/profile/${profileId}`
        );

        console.log(recipesResponse);

        setPosts(postsResponse.data.data);
        setRecipes(recipesResponse.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [userDetails.email, props.reload]);

  useEffect(() => {
    const loadImages = async () => {
      if (!Array.isArray(posts) || posts.length === 0) return;

      try {
        let imagePromises;
        if (Array.isArray(posts)) {
          imagePromises = posts.map(async (post) => {
            try {
              const response = await axios.get(
                `${import.meta.env.VITE_API_URL}/api/v1/posts/media/${
                  post.media
                }`
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
          imagePromises = Object.values(posts).map(async (post) => {
            try {
              const response = await axios.get(
                `${import.meta.env.VITE_API_URL}/api/v1/posts/media/${
                  post.media
                }`
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
  }, [posts]);

  const handleEdit = (item, type) => {
    setEditMode(true);
    setSelectedItem(item);
    setType(type);
    setContent(item.content);
    setTag(item.tag || "");
    setMedia(null);
    setPostId(item.postId);
    setRecipeName(item.recipeName);
    setRecipeIngredients(item.recipeIngredients);
    setRecipeInstructions(item.recipeInstructions);
    setCusineType(item.cusineType);
    setIsVegan(item.isVegan);
    setIsVegetarian(item.isVegetarian);
    setIsLactoseFree(item.isLactoseFree);
    setIsGlutenFree(item.isGlutenFree);
    setProfileId(userDetails.profileId);
    setPrepTime(item.prepTime);
    setRecipeId(item.recipeId);
    setOpened(true);
  };

  const handleDelete = async (item, type) => {
    try {
      const userId = userDetails.email;
      const profileId = userDetails.profileId;
      if (type === "recipe") {
        await axios.delete(
          `${import.meta.env.VITE_API_URL}/api/v1/recipes/${item.recipeId}`
        );
        notifications.show({
          title: "Success",
          message: "Recipe deleted successfully!",
          color: "green",
        });
        const updatedData = await axios.get(
          `${API_URL}/api/v1/recipes/profile/${profileId}`
        );
        setRecipes(updatedData.data);
      } else {
        await axios.delete(
          `${import.meta.env.VITE_API_URL}/api/v1/posts?postId=${item.postId}`
        );
        notifications.show({
          title: "Success",
          message: "Post deleted successfully!",
          color: "green",
        });
        const updatedData = await axios.get(
          `${import.meta.env.VITE_API_URL}/api/v1/posts/${userId}`
        );
        setPosts(updatedData.data.data);
      }
    } catch (error) {
      notifications.show({
        title: "Error",
        message: error.response
          ? `Error: ${error.response.data.message}`
          : "An unexpected error occurred",
        color: "red",
      });
    }
  };

  // Save changes
  const handleSave = async () => {
    const userId = userDetails.email;
    const profileId = userDetails.profileId;
    const url =
      type === "post"
        ? `${import.meta.env.VITE_API_URL}/api/v1/posts`
        : `${import.meta.env.VITE_API_URL}/api/v1/recipes`;
    try {
      const formData = {};
      if (type === "post") {
        formData["postId"] = postId;
        formData["content"] = content;
        formData["tag"] = tag;
      } else {
        formData["recipeId"] = recipeId;
        formData["recipeName"] = recipeName;
        formData["recipeIngredients"] = recipeIngredients;
        formData["recipeInstructions"] = recipeInstructions;
        formData["cusineType"] = cusineType;
        formData["isVegan"] = isVegan;
        formData["isVegetarian"] = isVegetarian;
        formData["isLactoseFree"] = isLactoseFree;
        formData["isGlutenFree"] = isGlutenFree;
        formData["profileId"] = profileId;
        formData["prepTime"] = prepTime;
      }

      console.log(formData);
      await axios.put(url, formData);
      setOpened(false);

      const updatedData =
        type === "post"
          ? await axios.get(
              `${import.meta.env.VITE_API_URL}/api/v1/posts/${userId}`
            )
          : await axios.get(
              `${
                import.meta.env.VITE_API_URL
              }/api/v1/recipes/profile/${profileId}`
            );
      type === "post"
        ? setPosts(updatedData.data.data)
        : setRecipes(updatedData.data);
    } catch (error) {
      console.error("Error saving changes:", error);
    }
  };

  const chipStyles = (condition) => ({
    backgroundColor: condition ? "#000411" : "#efcb68",
    color: "#fff",
  });

  return (
    <div>
      {/* Posts */}
      <Text size="lg" weight={600} mt="lg" mb="md">
        Posts
      </Text>
      <Grid gap={10}>
        {posts && posts?.length > 0 ? (
          posts.map((post) => (
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
                  <div
                    style={{
                      display: "flex",
                      alignItems: "center",
                      gap: "10px",
                      marginLeft: "10px",
                      padding: "md",
                      position: "absolute",
                      right: 10,
                    }}
                  >
                    <ActionIcon
                      className="item"
                      variant="light"
                      radius="md"
                      size={36}
                      onClick={() => handleEdit(post, "post")}
                    >
                      <IconEdit className={classes.like} stroke={1.5} />
                    </ActionIcon>
                    <ActionIcon
                      className="item"
                      variant="light"
                      radius="md"
                      size={36}
                      onClick={() => handleDelete(post, "post")}
                    >
                      <IconX className={classes.delete_icon} stroke={1.5} />
                    </ActionIcon>
                  </div>
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
      {/* Recipes */}
      <Text size="lg" weight={600} mt="lg" mb="md">
        Recipes
      </Text>
      <Grid gap={10}>
        {recipes && recipes?.length > 0 ? (
          recipes.map((recipe) => (
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
                <div
                  style={{
                    display: "flex",
                    alignItems: "center",
                    gap: "10px",
                    marginLeft: "10px",
                    padding: "md",
                  }}
                >
                  <ActionIcon
                    className="item"
                    variant="light"
                    radius="md"
                    size={36}
                    onClick={() => handleEdit(recipe, "recipe")}
                  >
                    <IconEdit className={classes.like} stroke={1.5} />
                  </ActionIcon>
                  <ActionIcon
                    className="item"
                    variant="light"
                    radius="md"
                    size={36}
                    onClick={() => handleDelete(recipe, "recipe")}
                  >
                    <IconX className={classes.delete_icon} stroke={1.5} />
                  </ActionIcon>
                </div>
              </Card>
            </Grid.Col>
          ))
        ) : (
          <Text>No recipes available</Text>
        )}
      </Grid>

      {/* Modal for editing */}
      <PostRecipeModal
        opened={opened}
        onClose={() => setOpened(false)}
        title={
          editMode ? `Edit ${type === "post" ? "Post" : "Recipe"}` : "Add Item"
        }
        content={content}
        tag={tag}
        media={media}
        setContent={setContent}
        type={type}
        setTag={setTag}
        setMedia={setMedia}
        onSubmit={handleSave}
        recipeName={recipeName}
        recipeIngredients={recipeIngredients}
        recipeInstructions={recipeInstructions}
        cusineType={cusineType}
        isVegan={isVegan}
        isVegetarian={isVegetarian}
        isLactoseFree={isLactoseFree}
        isGlutenFree={isGlutenFree}
        profileId={profileId}
        prepTime={prepTime}
        setRecipeName={setRecipeName}
        setRecipeIngredients={setRecipeIngredients}
        setRecipeInstructions={setRecipeInstructions}
        setCusineType={setCusineType}
        setIsVegan={setIsVegan}
        setIsVegetarian={setIsVegetarian}
        setIsLactoseFree={setIsLactoseFree}
        setIsGlutenFree={setIsGlutenFree}
        setProfileId={setProfileId}
        setPrepTime={setPrepTime}
      />
    </div>
  );
};

export default UserPostsRecipes;
