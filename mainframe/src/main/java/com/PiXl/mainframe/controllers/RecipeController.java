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

@RestController
@RequestMapping("/api/v1/recipes")
@CrossOrigin
public class RecipeController {

	@Autowired
	private RecipesService recServ;
	@Autowired
	private ProfileService profileService;

	@GetMapping
	public ResponseEntity<List<RecipeEntity>> getRecipes() {
		return ResponseEntity.ok(recServ.getAllRecipes());
	}

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
				Boolean.valueOf(filters.get("isGlutenFree")),
				Boolean.valueOf(filters.get("isVegan")),
				Boolean.valueOf(filters.get("isVegetarian")),
				Boolean.valueOf(filters.get("isLactoseFree")));

		List<RecipeEntity> recipes = recServ.getFilteredRecipes(filter);

		return ResponseEntity.ok(recipes);
	}

	@GetMapping("/{recipe_id}")
	public ResponseEntity<RecipeEntity> getRecipesById(@PathVariable Long recipe_id) {
		return ResponseEntity.ok(recServ.getRecipesById(recipe_id));
	}

	@GetMapping("/profile/{profile_id}")
	public ResponseEntity<List<RecipeEntity>> getRecipesByProfile(@PathVariable Long profile_id) {
		return ResponseEntity.ok(recServ.getRecipesByProfileId(profile_id));
	}

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
		if (savedPost == null) {
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.NOT_FOUND);
		} else {
			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> editRecipe(@RequestBody RecipeEntity json) {
		RecipeEntity savedPost = recServ.editRecipe(json);
		if (savedPost == null) {
			return ResponseHandler.generateResponse("Error editing post.", HttpStatus.NOT_FOUND);
		} else {
			return ResponseHandler.generateResponse("Post Edited!", HttpStatus.OK, savedPost);
		}
	}
}
