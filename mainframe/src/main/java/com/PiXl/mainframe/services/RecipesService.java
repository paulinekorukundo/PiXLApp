package com.PiXl.mainframe.services;

import java.util.List;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.entities.RecipeEntity;
import com.PiXl.mainframe.models.Recipe;

public interface RecipesService {
	// General
	List<RecipeEntity> getAllRecipes();
	RecipeEntity getRecipesById(long recipeId);
	List<RecipeEntity> getRecipesByProfileId(long profileId);
	
	RecipeEntity saveRecipe(RecipeEntity recipe);
	RecipeEntity editRecipe(RecipeEntity recipe);
	void deleteRecipe(long recipeId);
	
	// Filter Recipes
	List<RecipeEntity> getFilteredRecipes(RecipeFilter filter);
	List<RecipeEntity> getRecipesBasedOnIngredients(String ingredients);
	List<RecipeEntity> getRecipesFilteredByPrepTime(long minTime, long maxTime);
}
