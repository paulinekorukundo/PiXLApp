package com.PiXl.mainframe.services;

import java.util.List;

import com.PiXl.mainframe.models.Recipe;

public interface RecipesService {
	// General
	List<Recipe> getAllRecipes();
	Recipe getRecipesById(long recipeId);
	List<Recipe> getRecipesByProfileId(long profileId);
	
	Recipe saveRecipe(Recipe recipe);
	Recipe editRecipe(Recipe recipe);
	void deleteRecipe(long recipeId);
	
	// Filter Recipes
	List<Recipe> getFilteredRecipes(boolean isVegan, boolean isVegetarian, boolean isLactoseFree, boolean isGlutenFree);
	List<Recipe> getRecipesBasedOnIngredients(String ingredients);
	List<Recipe> getRecipesFilteredByPrepTime(long minTime, long maxTime);
}
