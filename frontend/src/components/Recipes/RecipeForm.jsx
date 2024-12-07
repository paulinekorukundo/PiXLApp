/* eslint-disable react/prop-types */
import React, { useEffect, useState } from "react";
import { Modal, TextInput, Button, Group } from "@mantine/core";
import { IconChefHat, IconClock } from "@tabler/icons-react";
import axios from "axios";
import { useDisclosure } from "@mantine/hooks";
import classes from "../../assets/BadgeCard.module.css";
import { useAppContext } from "../../context/AppContext";
import { notifications, showNotification } from "@mantine/notifications";
import {
  Checkbox,
  FormControlLabel,
  FormGroup,
  InputAdornment,
  TextField,
} from "@mui/material";

function RecipeForm(props) {
  // User
  const { userDetails } = useAppContext();

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);

  const [recipeData, setRecipeData] = useState({
    recipeName: "",
    recipeIngredients: "",
    recipeInstructions: "",
    cuisineType: "",
    isVegan: false,
    isVegetarian: false,
    isLactoseFree: false,
    isGlutenFree: false,
    profile: userDetails?.profileId || 0,
    prepTime: 0,
  });

  const clock_icon = <IconClock className="image-icon" stroke={1.5} />;
  const add_icon = <IconChefHat className={classes.like} stroke={1.5} />;

  const [message, setMessage] = useState("");

  // const RECIPE_URL = import.meta.env.VITE_API_URL + "/api/v1/recipes";

  const handleChange = (event) => {
    const { name, value, type, checked } = event.target;
  
    setRecipeData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : type === "number" ? parseFloat(value) : value,
    }));
  };
  

  const handleSubmit = async () => {
    console.log("Sending Data: ", recipeData);
    try {
      await axios.post(
        import.meta.env.VITE_API_URL + "/api/v1/recipes",
        recipeData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      setMessage("Recipe created successfully!");
      notifications.show({
        title: "Success",
        message: "Recipe created successfully!",
        color: "green",
      });
      if (props.needsReload) {
        props.needsReload((prevState) => !prevState);
      }

      close();
    } catch (error) {
      console.error("Error saving recipe:", error);
      const errorMessage = error.response
        ? `Error: ${error.response.data.message}`
        : "An unexpected error occurred";
      setMessage(errorMessage);

      notifications.show({
        title: "Error",
        message: errorMessage,
        color: "red",
      });
    }
  };

  // useEffect(() => {
  //   showNotification({
  //     title: "Success",
  //     message: "Post created successfully!",
  //     color: "green",
  //   });
  // }, []);

  return (
    <>
      <Button
        radius="md"
        mt="xl"
        size="md"
        variant="default"
        onClick={open}
        leftSection={add_icon}
      >
        Add Recipe
      </Button>

      <Modal opened={opened} onClose={close} title="Add Recipe">
        <TextInput
          label="Recipe Name"
          placeholder="Recipe Name"
          value={recipeData.recipeName}
          onChange={handleChange}
          name="recipeName"
        />
        <TextField
          fullWidth
          multiline
          rows={4}
          label="Ingredients"
          placeholder="Ingredients"
          variant="filled"
          value={recipeData.recipeIngredients}
          onChange={handleChange}
          name="recipeIngredients"
        />

        <TextField
          fullWidth
          multiline
          rows={8}
          label="Instructions"
          placeholder="Instructions"
          variant="filled"
          value={recipeData.recipeInstructions}
          onChange={handleChange}
          name="recipeInstructions"
        />
        <TextInput
          label="Cuisine Type"
          placeholder="Cuisine Type"
          value={recipeData.cuisineType}
          onChange={handleChange}
          name="cuisineType"
        />
        <FormGroup> Dietary Restrictions
        <FormControlLabel
          control={
            <Checkbox
              name="isVegan"
              checked={recipeData.isVegan}
              onChange={handleChange}
            />
          }
          label="Vegan"
        />
        <FormControlLabel
          control={
            <Checkbox
              name="isLactoseFree"
              checked={recipeData.isLactoseFree}
              onChange={handleChange}
            />
          }
          label="Lactose Free"
        />
        <FormControlLabel
          control={
            <Checkbox
              name="isVegetarian"
              checked={recipeData.isVegetarian}
              onChange={handleChange}
            />
          }
          label="Vegetarian"
        />
        <FormControlLabel
          control={
            <Checkbox
              name="isGlutenFree"
              checked={recipeData.isGlutenFree}
              onChange={handleChange}
            />
          }
          label="Gluten Free"
        />
        </FormGroup>
         
        {/* <TextInput
          label="Preparation Time"
          placeholder="Preparation Time"
          value={recipeData.prepTime}
          rightSection={clock_icon}
          onChange={handleChange}
          name="prepTime"
        /> */}
        <TextField
          fullWidth
          id="filled-number"
          label="Preparation Time"
          type="number"
          variant="filled"
          // slotProps={{
          //   inputLabel: {
          //     shrink: true,
          //   },
          // }}
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">minutes</InputAdornment>
            ),
          }}
          value={recipeData.prepTime}
          // leftSection={clock_icon}
          onChange={handleChange}
          name="prepTime"
        />
        <Group position="right" mt="md">
          <Button onClick={handleSubmit}>Save Recipe</Button>
        </Group>
      </Modal>
    </>
  );
}

export default RecipeForm;
