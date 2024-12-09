/* eslint-disable react/prop-types */
import React, { useState } from "react";
import { Modal, TextInput, Button, Group } from "@mantine/core";
import { IconChefHat } from "@tabler/icons-react";
import axios from "axios";
import { useDisclosure } from "@mantine/hooks";
import classes from "../../assets/BadgeCard.module.css";
import { useAppContext } from "../../context/AppContext";
import { notifications } from "@mantine/notifications";
import {
  Checkbox,
  FormControlLabel,
  FormGroup,
  InputAdornment,
  TextField,
} from "@mui/material";

/**
 * RecipeForm Component
 *
 * This component provides a modal form interface for creating and submitting new recipes.
 * Users can enter various details such as:
 * - Recipe name
 * - Ingredients
 * - Instructions
 * - Cuisine type
 * - Dietary restrictions (vegan, vegetarian, lactose-free, gluten-free)
 * - Preparation time
 *
 * Upon submission, the component sends a POST request to the server to save the recipe.
 * If the submission is successful, a success notification is shown and the parent component
 * can optionally trigger a reload of the recipe list. If there's an error, an error notification
 * is displayed.
 *
 * @component
 *
 * @prop {Function} [needsReload] - An optional state setter function from the parent component.
 *   If provided, it will be called after a successful recipe creation to request that the parent
 *   component reload its data (e.g., a list of recipes).
 */

function RecipeForm(props) {
  // User
  const { userDetails } = useAppContext();

  const initialRecipeData = {
    recipeName: "",
    recipeIngredients: "",
    recipeInstructions: "",
    cusineType: "",
    isVegan: false,
    isVegetarian: false,
    isLactoseFree: false,
    isGlutenFree: false,
    profileId: userDetails?.profileId || 0,
    prepTime: 0,
  };

  // Create a post
  const [opened, { open, close }] = useDisclosure(false);

  const [recipeData, setRecipeData] = useState(initialRecipeData);
  const add_icon = <IconChefHat className={classes.like} stroke={1.5} />;

  const [message, setMessage] = useState("");
  const handleChange = (event) => {
    const { name, value, type, checked } = event.target;
    console.log(event.target);
    setRecipeData((prevData) => ({
      ...prevData,
      [name]: type === "checkbox" ? checked : value,
    }));
  };

  const handleOpen = () => {
    setRecipeData(initialRecipeData); 
    open(); 
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

  return (
    <>
      <Button
        radius="md"
        mt="xl"
        size="md"
        variant="default"
        onClick={handleOpen}
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
          label="Cusine Type"
          placeholder="Cusine Type"
          value={recipeData.cusineType}
          onChange={handleChange}
          name="cusineType"
        />
        <FormGroup>
          {" "}
          Dietary Restrictions
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
        <TextField
          fullWidth
          id="filled-number"
          label="Preparation Time"
          type="number"
          variant="filled"
          InputProps={{
            endAdornment: (
              <InputAdornment position="end">minutes</InputAdornment>
            ),
          }}
          value={recipeData.prepTime}
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
