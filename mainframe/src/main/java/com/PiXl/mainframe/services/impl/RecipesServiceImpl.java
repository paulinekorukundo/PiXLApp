package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.entities.RecipeEntity;
import com.PiXl.mainframe.models.Recipe;
import com.PiXl.mainframe.repositories.RecipesRepository;
import com.PiXl.mainframe.services.RecipesService;

@Service
public class RecipesServiceImpl implements RecipesService {
	
	@Autowired
	private RecipesRepository recRepo;
	
	@Override
	public List<Recipe> getAllRecipes() {
		List<Recipe> recipes = recRepo.findAll().stream()
				.map(this::convertToRecipeModel)
				.collect(Collectors.toList());
		return recipes;
	}

	@Override
	public Recipe getRecipesById(long recipeId) {
		Optional<RecipeEntity> re = recRepo.findByRecipeId(recipeId);
		if(re.isEmpty()) {
			throw new IllegalArgumentException("Recipe does not exist!");
		}
		
		return convertToRecipeModel(re.get()); 
	}

	@Override
	public List<Recipe> getRecipesByProfileId(long profileId) {
		List<RecipeEntity> recipes = recRepo.findAll();
		return recipes.stream()
				.filter(x -> x.getProfile().getProfileId() == profileId)
				.map(this::convertToRecipeModel)
				.collect(Collectors.toList());
	}

	@Override
	public Recipe saveRecipe(Recipe recipe) {
		return convertToRecipeModel(recRepo.save(convertToRecipeEntity(recipe)));
	}
	
	@Override
	public Recipe editRecipe(Recipe rec) {
		if(recRepo.existsById(rec.getRecipeId())) {
			return convertToRecipeModel(recRepo.save(convertToRecipeEntity(rec)));
		}else {
			throw new IllegalArgumentException("Recipe does not exist!");
		}
	}

	@Override
	public void deleteRecipe(long recipeId) {
		if(!recRepo.existsById(recipeId)) {
			throw new IllegalArgumentException("Recipe does not exist!");
		}
		recRepo.deleteById(recipeId);
	}

	@Override
	public List<Recipe> getFilteredRecipes(RecipeFilter filter) {
		return recRepo.findRecipes(filter).stream()
				.map(this::convertToRecipeModel)
				.collect(Collectors.toList());
	}

	@Override
	public List<Recipe> getRecipesBasedOnIngredients(String ingredients) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Recipe> getRecipesFilteredByPrepTime(long minTime, long maxTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Recipe convertToRecipeModel(RecipeEntity re) {
		return new Recipe(re);
	}
	private RecipeEntity convertToRecipeEntity(Recipe re) {
		return new RecipeEntity(re);
	}

}
