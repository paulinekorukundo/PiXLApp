package com.PiXl.mainframe.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.entities.ProfileEntity;
import com.PiXl.mainframe.entities.RecipeEntity;
import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.models.Profile;
import com.PiXl.mainframe.services.ProfileService;
import com.PiXl.mainframe.services.RecipesService;

import jakarta.servlet.http.HttpServletRequest;


/**
 * Controller class that handles all HTTP requests related to recipes.
 * It provides endpoints for retrieving, filtering, creating, editing,
 * and deleting recipes.
 */
@RestController
@RequestMapping("/api/v1/recipes")
@CrossOrigin
public class RecipeController {

	@Autowired
	private RecipesService recServ;
	@Autowired
	private ProfileService profileService;

	/**
     * Retrieves all available recipes.
     *
     * @return A ResponseEntity containing a list of all RecipeEntity objects.
     */
	@GetMapping
	public ResponseEntity<List<RecipeEntity>> getRecipes() {
		return ResponseEntity.ok(recServ.getAllRecipes());
	}

	/**
     * Filters recipes based on query parameters supplied".
     * The query parameters should be provided in the URL and may include:
     * - isGlutenFree
     * - isVegan
     * - isVegetarian
     * - isLactoseFree
     *
     * @param request The HttpServletRequest containing the query parameters.
     * @return A ResponseEntity containing a filtered list of RecipeEntity objects.
     */
	@GetMapping("/filter/**")
	public ResponseEntity<List<RecipeEntity>> filterRecipes(HttpServletRequest request) {
		String fullPath = request.getRequestURI(); // Extract the full path
		String queryString = fullPath.substring(fullPath.indexOf("/filter/") + "/filter/".length());

		Map<String, String> filters = Arrays.stream(queryString.split("&"))
				.map(param -> param.split("="))
				.collect(Collectors.toMap(
						arr -> arr[0],
						arr -> arr.length > 1 ? arr[1] : ""));

		RecipeFilter filter = new RecipeFilter(
				Boolean.valueOf(filters.get("isGlutenFree")) == false ? null : Boolean.valueOf(filters.get("isGlutenFree")),
				Boolean.valueOf(filters.get("isVegan")) == false ? null : Boolean.valueOf(filters.get("isVegan")),
				Boolean.valueOf(filters.get("isVegetarian")) == false ? null : Boolean.valueOf(filters.get("isVegetarian")),
				Boolean.valueOf(filters.get("isLactoseFree")) == false ? null : Boolean.valueOf(filters.get("isLactoseFree")));

		List<RecipeEntity> recipes = recServ.getFilteredRecipes(filter);

		return ResponseEntity.ok(recipes);
	}

	/**
     * Retrieves a recipe by its ID.
     *
     * @param recipe_id The ID of the recipe to retrieve.
     * @return A ResponseEntity containing the requested RecipeEntity object.
     */
	@GetMapping("/{recipe_id}")
	public ResponseEntity<RecipeEntity> getRecipesById(@PathVariable Long recipe_id) {
		return ResponseEntity.ok(recServ.getRecipesById(recipe_id));
	}

	/**
     * Retrieves all recipes associated with a specific profile.
     *
     * @param profile_id The ID of the profile.
     * @return A ResponseEntity containing a list of RecipeEntity objects linked to the given profile ID.
     */
	@GetMapping("/profile/{profile_id}")
	public ResponseEntity<List<RecipeEntity>> getRecipesByProfile(@PathVariable Long profile_id) {
		return ResponseEntity.ok(recServ.getRecipesByProfileId(profile_id));
	}

	/**
     * Saves a new recipe. The recipe details should be provided in the request body as JSON.
     * Expected keys include:
     * - profileId (Long)
     * - recipeName (String)
     * - recipeIngredients (String)
     * - recipeInstructions (String)
     * - cusineType (String)
     * - isVegan (Boolean)
     * - isVegetarian (Boolean)
     * - isLactoseFree (Boolean)
     * - isGlutenFree (Boolean)
     * - prepTime (Double)
     *
     * @param json A map containing the details of the recipe.
     * @return A ResponseEntity with a success or error message along with the saved RecipeEntity.
     */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> saveRecipe(@RequestBody Map<String, String> json) {
		Profile prof = profileService.getProfile(Long.valueOf(json.get("profileId")).longValue()).get();
		RecipeEntity recipeToSave = new RecipeEntity(json.get("recipeName"), new ProfileEntity(prof),
				json.get("recipeIngredients"), json.get("recipeInstructions"), json.get("cusineType"),
				Boolean.valueOf(json.get("isVegan")), Boolean.valueOf(json.get("isVegetarian")), 
				Boolean.valueOf(json.get("isLactoseFree")), Boolean.valueOf(json.get("isGlutenFree")), 
				Double.valueOf(json.get("prepTime")).doubleValue());

		RecipeEntity savedPost = recServ.saveRecipe(recipeToSave);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error saving recipe.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Recipe Saved!", HttpStatus.OK, savedPost);
		}
	}

	
	/**
     * Updates an existing recipe. The updated recipe details are provided in the request body.
     *
     * @param json The RecipeEntity object with updated fields.
     * @return A ResponseEntity with a success or error message along with the updated RecipeEntity.
     */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	private ResponseEntity<Object> editRecipe(@RequestBody Map<String, String> json) {
		Profile prof = profileService.getProfile(Long.valueOf(json.get("profileId")).longValue()).get();
		RecipeEntity recipeToSave = new RecipeEntity(json.get("recipeName"), new ProfileEntity(prof),
				json.get("recipeIngredients"), json.get("recipeInstructions"), json.get("cusineType"),
				Boolean.valueOf(json.get("isVegan")), Boolean.valueOf(json.get("isVegetarian")), 
				Boolean.valueOf(json.get("isLactoseFree")), Boolean.valueOf(json.get("isGlutenFree")), 
				Double.valueOf(json.get("prepTime")).doubleValue());
		recipeToSave.setRecipeId(Long.valueOf(json.get("recipeId")).longValue());
		RecipeEntity savedPost = recServ.editRecipe(recipeToSave);
		if (savedPost == null) {
			return ResponseHandler.generateResponse("Error editing recipe.", HttpStatus.NOT_FOUND);
		} else {
			return ResponseHandler.generateResponse("Recipe Edited!", HttpStatus.OK, savedPost);
		}
	}
	
	
	/**
	 *  Method to delete a recipe
	 *  
	 *  @param recipeId of the recipe to delete
	 *  @return boolean showing recipe is deleted or not
	 */
	@RequestMapping(value="/{recipeId}", method=RequestMethod.DELETE)
	@ResponseBody
	private ResponseEntity<Object> deleteRecipe(@PathVariable long recipeId) {
		recServ.deleteRecipe(recipeId);
		return ResponseHandler.generateResponse("Recipe Deleted!", HttpStatus.OK, true);
	}
}
