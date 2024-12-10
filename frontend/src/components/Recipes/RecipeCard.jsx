import React from "react";
import {
  Card,
  CardContent,
  Typography,
  Chip,
  Grid2,
  CardHeader,
  Divider,
} from "@mui/material";

const RecipeCard = ({ recipe }) => {
  const {
    recipeName,
    recipeIngredients,
    recipeInstructions,
    cusineType,
    isVegan,
    isVegetarian,
    isLactoseFree,
    isGlutenFree,
    prepTime,
    profile,
  } = recipe;

  const chipStyles = (condition) => ({
    backgroundColor: condition ? "#000411" : "#efcb68",
    color: condition ? "#fff" : "#000",
  });

  return (
    <Card
      sx={{
        maxWidth: 400,
        height: 350,
        margin: "auto",
        boxShadow: 3,
        transition: "box-shadow 0.3s ease-in-out",
        ":hover": {
          boxShadow: 10,
        },
      }}
    >
      <CardHeader
        title={`${recipeName}`}
        subheader={`By ${profile.firstName} ${profile.lastName}`}
        sx={{
          color: "#000411",
        }}
      />
      <Divider sx={{ backgroundColor: "#8ea8c3" }} />
      <CardContent>
        <Typography variant="body2" color="#160c28" gutterBottom>
          <strong>Cusine Type:</strong> {cusineType}
        </Typography>
        <Typography variant="body2" color="#160c28" gutterBottom>
          <strong>Ingredients:</strong> {recipeIngredients}
        </Typography>
        <Typography variant="body2" color="#160c28" gutterBottom>
          <strong>Instructions:</strong> {recipeInstructions}
        </Typography>
        <Typography variant="body2" color="#160c28">
          <strong>Prep Time:</strong> {prepTime} minutes
        </Typography>
        <Divider sx={{ backgroundColor: "#8ea8c3", marginY: 2 }} />
        <Grid2 container spacing={1}>
          <Grid2 item>
            <Chip
              label={`Vegan: ${isVegan ? "Yes" : "No"}`}
              size="small"
              sx={chipStyles(isVegan)}
            />
          </Grid2>
          <Grid2 item>
            <Chip
              label={`Vegetarian: ${isVegetarian ? "Yes" : "No"}`}
              size="small"
              sx={chipStyles(isVegetarian)}
            />
          </Grid2>
          <Grid2 item>
            <Chip
              label={`Lactose-Free: ${isLactoseFree ? "Yes" : "No"}`}
              size="small"
              sx={chipStyles(isLactoseFree)}
            />
          </Grid2>
          <Grid2 item>
            <Chip
              label={`Gluten-Free: ${isGlutenFree ? "Yes" : "No"}`}
              size="small"
              sx={chipStyles(isGlutenFree)}
            />
          </Grid2>
        </Grid2>
      </CardContent>
    </Card>
  );
};

export default RecipeCard;
