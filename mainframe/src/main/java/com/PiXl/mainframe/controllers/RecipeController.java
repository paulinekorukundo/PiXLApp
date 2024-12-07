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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.dto.RecipeFilter;
import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.models.Recipe;
import com.PiXl.mainframe.services.RecipesService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/recipes")
@CrossOrigin(origins = "http://localhost:5173")
public class RecipeController {
	
	@Autowired
	private RecipesService recServ;
	
	@GetMapping
    public ResponseEntity<List<Recipe>> getRecipes(){
    	return ResponseEntity.ok(recServ.getAllRecipes());
    }
	
	@GetMapping("/filter/**")
    public ResponseEntity<List<Recipe>> filterRecipes(HttpServletRequest request) {
        String fullPath = request.getRequestURI(); // Extract the full path
        String queryString = fullPath.substring(fullPath.indexOf("/filter/") + "/filter/".length());

        // Parse the query string into a map
        Map<String, String> filters = Arrays.stream(queryString.split("&"))
            .map(param -> param.split("="))
            .collect(Collectors.toMap(
                arr -> arr[0], 
                arr -> arr.length > 1 ? arr[1] : ""
            ));

        // Convert the map to filter conditions
        RecipeFilter filter = new RecipeFilter(
            Boolean.valueOf(filters.get("isGlutenFree")),
            Boolean.valueOf(filters.get("isVegan")),
            Boolean.valueOf(filters.get("isVegetarian")),
            Boolean.valueOf(filters.get("isLactoseFree"))
        );

        // Call the service layer to fetch filtered recipes
        List<Recipe> recipes = recServ.getFilteredRecipes(filter);

        return ResponseEntity.ok(recipes);
    }
	
	@GetMapping("/{recipe_id}")
    public ResponseEntity<Recipe> getRecipesById(@PathVariable Long recipe_id){
    	return ResponseEntity.ok(recServ.getRecipesById(recipe_id));
    }
	
	@GetMapping("/profile/{profile_id}")
    public ResponseEntity<List<Recipe>> getRecipesByProfile(@PathVariable Long profile_id){
    	return ResponseEntity.ok(recServ.getRecipesByProfileId(profile_id));
    }
	
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> saveRecipe(@RequestBody Recipe json){
		Recipe savedPost = recServ.saveRecipe(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> editRecipe(@RequestBody Recipe json){
		Recipe savedPost = recServ.editRecipe(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error editing post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Edited!", HttpStatus.OK, savedPost);
		}
	}
}
