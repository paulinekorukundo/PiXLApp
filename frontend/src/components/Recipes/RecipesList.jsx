import axios from "axios";
import { useEffect, useState } from "react";
import "../../assets/General.css";
import RecipeCard from "./RecipeCard";
import {
  Typography,
  FormControlLabel,
  Checkbox,
  Box,
  Button,
  Grid,
} from "@mui/material";
import Loading from "../Loading";

/**
 * RecipesList Component
 *
 * This component fetches and displays a list of recipes, allowing users to apply various dietary filters.
 * Users can filter recipes by properties such as gluten-free, vegan, vegetarian, and lactose-free. The
 *
 *
 * @component
 * *
 * @returns {JSX.Element} A list of filtered (or unfiltered) recipes displayed in a responsive grid.
 *
 */

function RecipesList() {
  const [recipes, setRecipes] = useState([]);
  const [filterConditions, setFilterConditions] = useState({
    isGlutenFree: false,
    isVegan: false,
    isVegetarian: false,
    isLactoseFree: false,
  });
  const [error] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const fetchRecipes = async (filterConditions) => {
    setIsLoading(true);
    try {
      const filters = { ...filterConditions };
      const query = new URLSearchParams(filters).toString();
      const noFiltersApplied = Object.values(filters).every((value) => !value);
      const response = noFiltersApplied
        ? await axios.get(import.meta.env.VITE_API_URL + `/api/v1/recipes`)
        : await axios.get(
            import.meta.env.VITE_API_URL + `/api/v1/recipes/filter/${query}`
          );

      console.log("Response: ", response.data);
      setRecipes(response.data || []);
    } catch (error) {
      console.error("Error fetching posts:", error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleFilterChange = (e) => {
    const { name, checked } = e.target;
    setFilterConditions((prev) => ({
      ...prev,
      [name]: checked,
    }));
  };

  const applyFilters = () => {
    const filters = { ...filterConditions };
    const noFiltersApplied = Object.values(filters).every((value) => !value);
    if (noFiltersApplied) {
      fetchRecipes();
    } else {
      fetchRecipes(filters);
    }
  };

  useEffect(() => {
    fetchRecipes({});
  }, []);

  if (error) {
    return (
      <Typography
        variant="h5"
        align="center"
        sx={{ marginTop: 4, color: "#cbf7ed" }}
      >
        {error}
      </Typography>
    );
  }

  return (
    <div>
      {isLoading && <Loading />}
      <div
        style={{
          minHeight: "100vh",
          color: "#cbf7ed",
        }}
      >
        <Box mb={4} sx={{ padding: 3, borderRadius: "8px" }}>
          <Typography
            variant="h4"
            align="center"
            gutterBottom
            sx={{ color: "#000411" }}
          >
            Recipe Filters
          </Typography>
          <Box display="flex" justifyContent="center" gap={2} flexWrap="wrap">
            <FormControlLabel
              control={
                <Checkbox
                  checked={filterConditions.isGlutenFree}
                  onChange={handleFilterChange}
                  name="isGlutenFree"
                  sx={{
                    color: "#000411",
                    "&.Mui-checked": { color: "#000411" },
                  }}
                />
              }
              label="Gluten Free"
              sx={{ color: "#000411" }}
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={filterConditions.isVegan}
                  onChange={handleFilterChange}
                  name="isVegan"
                  sx={{
                    color: "#000411",
                    "&.Mui-checked": { color: "#000411" },
                  }}
                />
              }
              label="Vegan"
              sx={{ color: "#000411" }}
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={filterConditions.isVegetarian}
                  onChange={handleFilterChange}
                  name="isVegetarian"
                  sx={{
                    color: "#000411",
                    "&.Mui-checked": { color: "#000411" },
                  }}
                />
              }
              label="Vegetarian"
              sx={{ color: "#000411" }}
            />
            <FormControlLabel
              control={
                <Checkbox
                  checked={filterConditions.isLactoseFree}
                  onChange={handleFilterChange}
                  name="isLactoseFree"
                  sx={{
                    color: "#000411",
                    "&.Mui-checked": { color: "#000411" },
                  }}
                />
              }
              label="Lactose Free"
              sx={{ color: "#000411" }}
            />
          </Box>
          <Box display="flex" justifyContent="center" mt={2}>
            <Button
              variant="contained"
              onClick={applyFilters}
              sx={{
                backgroundColor: "#000411",
                color: "whitesmoke",
                "&:hover": { color: "#000411", backgroundColor: "#fff" },
              }}
            >
              Apply
            </Button>
          </Box>
        </Box>

        <Grid container spacing={4}>
          {recipes.map((recipe) => (
            <Grid item xs={12} sm={6} md={4} key={recipe.recipeId}>
              <RecipeCard recipe={recipe} />
            </Grid>
          ))}
        </Grid>
      </div>
    </div>
  );
}

export default RecipesList;
