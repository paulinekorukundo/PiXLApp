package com.PiXl.mainframe.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.entities.RecipeEntity;
import com.PiXl.mainframe.repositories.RecipesRepository;
import com.PiXl.mainframe.services.RecipesService;

@Service
public class RecipesServiceImpl implements RecipesService {
	
	@Autowired
	private RecipesRepository recRepo;
	
	@Override
	public List<RecipeEntity> getAllRecipes() {
		return recRepo.findAll();
	}

	@Override
	public RecipeEntity getRecipesById(long recipeId) {
		Optional<RecipeEntity> re = recRepo.findByRecipeId(recipeId);
		if(re.isEmpty()) {
			return null;
		}
		
		return re.get();
	}

	@Override
	public List<RecipeEntity> getRecipesByProfileId(long profileId) {
		List<RecipeEntity> recipes = recRepo.findAll();
		return recipes.stream()
				.filter(x -> x.getProfile().getProfileId() == profileId)
				.collect(Collectors.toList());
	}

	@Override
	public RecipeEntity saveRecipe(RecipeEntity recipe) {
		return recRepo.save(recipe);
	}
	
	@Override
	public RecipeEntity editRecipe(RecipeEntity rec) {
		if(recRepo.existsById(rec.getRecipeId())) {
			return recRepo.save(rec);
		}else {
			throw new IllegalArgumentException("Recipe does not exist!");
		}
	}

	@Override
	public boolean deleteRecipe(long recipeId) {
		if(!recRepo.existsById(recipeId)) {
			throw new IllegalArgumentException("Recipe does not exist!");
		}
		recRepo.deleteById(recipeId);
		return true;
	}

	@Override
	public List<RecipeEntity> getFilteredRecipes(RecipeFilter filter) {
		return recRepo.findRecipes(filter).stream()
				.collect(Collectors.toList());
	}

	@Override
	public List<RecipeEntity> getRecipesBasedOnIngredients(String ingredients) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecipeEntity> getRecipesFilteredByPrepTime(long minTime, long maxTime) {
		// TODO Auto-generated method stub
		return null;
	}

}
