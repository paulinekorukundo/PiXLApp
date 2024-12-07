package com.PiXl.mainframe.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.entities.RecipeEntity;

import jakarta.persistence.criteria.Predicate;

public interface RecipesRepository extends JpaRepository<RecipeEntity, Long>, JpaSpecificationExecutor<RecipeEntity>{
	List<RecipeEntity> findAll();
	Optional<RecipeEntity> findByRecipeId(Long recipeId);
	
	List<RecipeEntity> findByRecipeIngredientsContainingIgnoreCase(String ingredient);
	List<RecipeEntity> findByCusineTypeContainsIgnoreCase(String cusine);
	List<RecipeEntity> findByIsGlutenFree(Boolean isGlutenFree);
	List<RecipeEntity> findByIsVegan(Boolean isVegan);
	List<RecipeEntity> findByIsVegetarian(Boolean isVegetarian);
	List<RecipeEntity> findByIsLactoseFree(Boolean isLactoseFree);
	
	default List<RecipeEntity> findRecipes(RecipeFilter filter) {
        return findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getIsGlutenFree() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isGlutenFree"), filter.getIsGlutenFree()));
            }
            if (filter.getIsVegan() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isVegan"), filter.getIsVegan()));
            }
            if (filter.getIsVegetarian() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isVegetarian"), filter.getIsVegetarian()));
            }
            if (filter.getIsLactoseFree() != null) {
                predicates.add(criteriaBuilder.equal(root.get("isLactoseFree"), filter.getIsLactoseFree()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }

	
	@SuppressWarnings("unchecked")
	RecipeEntity save(RecipeEntity recipe);
	
	void deleteById(Long recipeId);
    boolean existsById(Long recipeId);
    long count();
}
