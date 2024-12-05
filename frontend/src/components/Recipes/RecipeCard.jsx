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

  return (
    <Card sx={{ maxWidth: 345, margin: "auto", boxShadow: 3 }}>
      <CardHeader
        title={`${cusineType} Recipe`}
        subheader={`By ${profile.firstName} ${profile.lastName}`}
      />
      <Divider />
      <CardContent>
        <Typography variant="body1" color="text.secondary" gutterBottom>
          <strong>Ingredients:</strong> {recipeIngredients}
        </Typography>
        <Typography variant="body2" color="text.secondary" gutterBottom>
          <strong>Instructions:</strong> {recipeInstructions}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          <strong>Prep Time:</strong> {prepTime} minutes
        </Typography>
        <Divider sx={{ marginY: 2 }} />
        <Grid container spacing={1}>
          <Grid item>
            <Chip
              label={`Vegan: ${isVegan ? "Yes" : "No"}`}
              color={isVegan ? "success" : "default"}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Vegetarian: ${isVegetarian ? "Yes" : "No"}`}
              color={isVegetarian ? "success" : "default"}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Lactose-Free: ${isLactoseFree ? "Yes" : "No"}`}
              color={isLactoseFree ? "success" : "default"}
            />
          </Grid>
          <Grid item>
            <Chip
              label={`Gluten-Free: ${isGlutenFree ? "Yes" : "No"}`}
              color={isGlutenFree ? "success" : "default"}
            />
          </Grid>
        </Grid>
      </CardContent>
    </Card>
  );
};

export default RecipeCard;
