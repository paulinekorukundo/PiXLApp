import React from "react";
import { Modal, TextInput, FileInput, Group, Button } from "@mantine/core";
import {
  Checkbox,
  FormControlLabel,
  FormGroup,
  InputAdornment,
  TextField,
} from "@mui/material";

const PostRecipeModal = ({
  opened,
  onClose,
  title,
  content,
  tag,
  media,
  setContent,
  setTag,
  setMedia,
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
      {/* {type === "post" && (
        <FileInput
          clearable
          label="Upload Media"
          placeholder="Add Media"
          accept="image/png,image/jpeg"
          onChange={(media) => setMedia(media)}
        />
      )} */}
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
