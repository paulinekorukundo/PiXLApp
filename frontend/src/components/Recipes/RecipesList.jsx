import axios from "axios";
import React, { useEffect, useState } from "react";
import "../../assets/General.css";
import RecipeCard from "./RecipeCard";
import { Grid, Typography } from "@mui/material";

function RecipesList() {
  const [recipes, setRecipes] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error] = useState(null);

  useEffect(() => {
    const showRecipes = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/recipes"
        );
        console.log("Response: ", response.data);
        setRecipes(response.data || []);
      } catch (error) {
        console.error("Error fetching posts:", error);
      } finally {
        setLoading(false);
      }
    };

    showRecipes();
  }, []);

  if (loading) {
    return (
      <Typography variant="h5" align="center" sx={{ marginTop: 4 }}>
        Loading...
      </Typography>
    );
  }
  if (error) {
    return (
      <Typography variant="h5" align="center" sx={{ marginTop: 4 }}>
        {error}
      </Typography>
    );
  }

  return (
    <div>
      {/* <Typography
        variant="h4"
        align="center"
        gutterBottom
        sx={{ marginTop: 4 }}
      >
        All Recipes
      </Typography> */}
      <Grid container spacing={4}>
        {recipes.map((recipe) => (
          <Grid item xs={12} sm={6} md={4} key={recipe.recipeId}>
            <RecipeCard recipe={recipe} />
          </Grid>
        ))}
      </Grid>
    </div>
  );
}

export default RecipesList;
