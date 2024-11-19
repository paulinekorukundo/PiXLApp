package com.PiXl.mainframe.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PiXl.mainframe.entities.RecipeEntity;

public interface RecipesRepository extends JpaRepository<RecipeEntity, Long>{
	List<RecipeEntity> findAll();
	Optional<RecipeEntity> findByRecipeId(Long recipeId);
	
	List<RecipeEntity> findByRecipeIngredientsContainingIgnoreCase(String ingredient);
	List<RecipeEntity> findByCusineTypeContainsIgnoreCase(String cusine);
	List<RecipeEntity> findByIsGlutenFree(Boolean isGlutenFree);
	List<RecipeEntity> findByIsVegan(Boolean isVegan);
	List<RecipeEntity> findByIsVegetarian(Boolean isVegetarian);
	List<RecipeEntity> findByIsLactoseFree(Boolean isLactoseFree);

	
	@SuppressWarnings("unchecked")
	RecipeEntity save(RecipeEntity recipe);
	
	void deleteById(Long recipeId);
    boolean existsById(Long recipeId);
    long count();
}
