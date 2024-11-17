package com.PiXl.mainframe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.PiXl.mainframe.handler.ResponseHandler;
import com.PiXl.mainframe.models.Recipe;
import com.PiXl.mainframe.services.RecipesService;

@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {
	
	@Autowired
	private RecipesService recServ;
	
	@GetMapping
    public ResponseEntity<List<Recipe>> getRecipes(){
    	return ResponseEntity.ok(recServ.getAllRecipes());
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
	private ResponseEntity<Object> savePost(@RequestBody Recipe json){
		Recipe savedPost = recServ.saveRecipe(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error saving post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Saved!", HttpStatus.OK, savedPost);
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	private ResponseEntity<Object> editPost(@RequestBody Recipe json){
		Recipe savedPost = recServ.editRecipe(json);
		if(savedPost == null) {
			return ResponseHandler.generateResponse("Error editing post.", HttpStatus.NOT_FOUND);
		}else {
			return ResponseHandler.generateResponse("Post Edited!", HttpStatus.OK, savedPost);
		}
	}
}
