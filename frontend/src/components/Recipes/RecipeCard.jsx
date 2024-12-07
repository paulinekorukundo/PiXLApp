import React from "react";
import {
  Card,
  CardContent,
  Typography,
  Chip,
  Grid,
  CardHeader,
  Divider,
} from "@mui/material";

const RecipeCard = ({ recipe }) => {
  const {
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
    color: "#fff",
  });

  return (
    <Card
      sx={{
        maxWidth: 400,
        height: 350,
        margin: "auto",
        boxShadow: 3,
      }}
    >
      <CardHeader
        title={`${cusineType} Recipe`}
        subheader={`By ${profile.firstName} ${profile.lastName}`}
        sx={{
          color: "#000411",
        }}
      />
      <Divider sx={{ backgroundColor: "#8ea8c3" }} />
      <CardContent>
        <Typography variant="body1" color="#160c28" gutterBottom>
          <strong>Ingredients:</strong> {recipeIngredients}
        </Typography>
        <Typography variant="body2" color="#160c28" gutterBottom>
          <strong>Instructions:</strong> {recipeInstructions}
        </Typography>
        <Typography variant="body2" color="#160c28">
          <strong>Prep Time:</strong> {prepTime} minutes
        </Typography>
        <Divider sx={{ backgroundColor: "#8ea8c3", marginY: 2 }} />
        <Grid container spacing={1}>
          <Grid item>
            <Chip
              label={`Vegan: ${isVegan ? "Yes" : "No"}`}
              sx={chipStyles(isVegan)}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Vegetarian: ${isVegetarian ? "Yes" : "No"}`}
              sx={chipStyles(isVegetarian)}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Lactose-Free: ${isLactoseFree ? "Yes" : "No"}`}
              sx={chipStyles(isLactoseFree)}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Gluten-Free: ${isGlutenFree ? "Yes" : "No"}`}
              sx={chipStyles(isGlutenFree)}
            />
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
};

export default RecipeCard;
