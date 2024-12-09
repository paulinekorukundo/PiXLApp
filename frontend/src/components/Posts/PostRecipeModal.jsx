import React from "react";
import { Modal, TextInput, FileInput, Group, Button } from "@mantine/core";
import {
  Checkbox,
  FormControlLabel,
  FormGroup,
  InputAdornment,
  TextField,
} from "@mui/material";

/**
 * PostRecipeModal Component
 *
 * This component renders a modal for creating or editing posts and recipes. Depending on the `type` prop,
 * it shows the appropriate form fields:
 * - If `type === "post"`, it displays inputs for content and an optional tag.
 * - If `type === "recipe"`, it displays fields for recipe name, ingredients, instructions, cuisine type,
 *   dietary restrictions, and preparation time.
 *
 * The component uses Mantine's Modal and MUI's TextField, Checkbox, and related components for UI.
 *
 * @component
 * @param {Object} props - The component props.
 * @param {boolean} props.opened - Controls whether the modal is visible.
 * @param {Function} props.onClose - Function to close the modal.
 * @param {string} props.title - The title displayed on the modal.
 * @param {string} [props.content] - Content text for the post mode.
 * @param {string} [props.tag] - Tag for the post mode.
 * @param {Function} [props.setContent] - Callback to update the content state.
 * @param {Function} [props.setTag] - Callback to update the tag state.
 * @param {string} props.type - Determines which form to show: "post" or "recipe".
 * @param {Function} props.onSubmit - Callback to handle form submission.
 * @param {string} [props.recipeName] - Name of the recipe for recipe mode.
 * @param {string} [props.recipeIngredients] - Ingredients for the recipe.
 * @param {string} [props.recipeInstructions] - Instructions for the recipe.
 * @param {string} [props.cusineType] - Cuisine type of the recipe.
 * @param {boolean} [props.isVegan] - Whether the recipe is vegan.
 * @param {boolean} [props.isVegetarian] - Whether the recipe is vegetarian.
 * @param {boolean} [props.isLactoseFree] - Whether the recipe is lactose-free.
 * @param {boolean} [props.isGlutenFree] - Whether the recipe is gluten-free.
 * @param {string|number} [props.profileId] - ID of the profile associated with the recipe.
 * @param {string|number} [props.prepTime] - Preparation time for the recipe in minutes.
 * @param {Function} [props.setRecipeName] - Callback to update the recipeName state.
 * @param {Function} [props.setRecipeIngredients] - Callback to update the recipeIngredients state.
 * @param {Function} [props.setRecipeInstructions] - Callback to update the recipeInstructions state.
 * @param {Function} [props.setCusineType] - Callback to update the cusineType state.
 * @param {Function} [props.setIsVegan] - Callback to update the isVegan state.
 * @param {Function} [props.setIsVegetarian] - Callback to update the isVegetarian state.
 * @param {Function} [props.setIsLactoseFree] - Callback to update the isLactoseFree state.
 * @param {Function} [props.setIsGlutenFree] - Callback to update the isGlutenFree state.
 * @param {Function} [props.setProfileId] - Callback to update the profileId state.
 * @param {Function} [props.setPrepTime] - Callback to update the prepTime state.
 *
 * />
 */
const PostRecipeModal = ({
  opened,
  onClose,
  title,
  content,
  tag,
  setContent,
  setTag,
  type,
  onSubmit,
  recipeName,
  recipeIngredients,
  recipeInstructions,
  cusineType,
  isVegan,
  isVegetarian,
  isLactoseFree,
  isGlutenFree,
  profileId,
  prepTime,
  setRecipeName,
  setRecipeIngredients,
  setRecipeInstructions,
  setCusineType,
  setIsVegan,
  setIsVegetarian,
  setIsLactoseFree,
  setIsGlutenFree,
  setProfileId,
  setPrepTime,
}) => {
  return (
    <Modal opened={opened} onClose={onClose} title={title}>
      {type === "post" && (
        <TextInput
          label="Content"
          placeholder="Description"
          value={content}
          onChange={(e) => setContent(e.target.value)}
          required
        />
      )}

      {type === "post" && (
        <TextInput
          label="Tag"
          placeholder="Add Tag"
          value={tag}
          onChange={(e) => setTag(e.target.value)}
        />
      )}
      {type === "recipe" && (
        <TextInput
          label="Recipe Name"
          placeholder="Recipe Name"
          value={recipeName}
          onChange={(e) => setRecipeName(e.target.value)}
        />
      )}
      {type === "recipe" && (
        <TextField
          fullWidth
          multiline
          rows={3}
          label="Ingredients"
          placeholder="Ingredients"
          variant="filled"
          value={recipeIngredients}
          onChange={(e) => setRecipeIngredients(e.target.value)}
          name="recipeIngredients"
        />
      )}
      {type === "recipe" && (
        <TextField
          fullWidth
          multiline
          rows={4}
          label="Instructions"
          placeholder="Instructions"
          variant="filled"
          value={recipeInstructions}
          onChange={(e) => setRecipeInstructions(e.target.value)}
          name="recipeInstructions"
        />
      )}
      {type === "recipe" && (
        <TextInput
          label="Cusine Type"
          placeholder="Cusine Type"
          value={cusineType}
          onChange={(e) => setCusineType(e.target.value)}
          name="cusineType"
        />
      )}
      {type === "recipe" && (
        <FormGroup>
          {" "}
          Dietary Restrictions
          <FormControlLabel
            control={
              <Checkbox
                name="isVegan"
                checked={isVegan}
                onChange={(e) => setIsVegan(e.checked)}
              />
            }
            label="Vegan"
          />
          <FormControlLabel
            control={
              <Checkbox
                name="isLactoseFree"
                checked={isLactoseFree}
                onChange={(e) => setIsLactoseFree(e.checked)}
              />
            }
            label="Lactose Free"
          />
          <FormControlLabel
            control={
              <Checkbox
                name="isVegetarian"
                checked={isVegetarian}
                onChange={(e) => setIsVegetarian(e.checked)}
              />
            }
            label="Vegetarian"
          />
          <FormControlLabel
            control={
              <Checkbox
                name="isGlutenFree"
                checked={isGlutenFree}
                onChange={(e) => setIsGlutenFree(e.checked)}
              />
            }
            label="Gluten Free"
          />
        </FormGroup>
      )}
      {type === "recipe" && (
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
          value={prepTime}
          onChange={(e) => setPrepTime(e.target.value)}
          name="prepTime"
        />
      )}
      <Group position="right" mt="md">
        <Button onClick={onSubmit}>Save</Button>
      </Group>
    </Modal>
  );
};

export default PostRecipeModal;
