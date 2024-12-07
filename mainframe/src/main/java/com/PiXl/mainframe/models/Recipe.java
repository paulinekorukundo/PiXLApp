package com.PiXl.mainframe.models;

import com.PiXl.mainframe.entities.RecipeEntity;

public class Recipe {
	private Long recipeId;
	private String recipeName;
	private String recipeIngredients;
	private String recipeInstructions;
	private String cusineType;
	private Boolean isVegan;
	private Boolean isVegetarian;
	private Boolean isLactoseFree;
	private Boolean isGlutenFree;
	private Profile profile;
	private Double prepTime;
	
	public Recipe() {
		super();
	}
	
	public Recipe(String recipeName, String recipeIngredients, String recipeInstructions, String cusineType,
			Boolean isVegan, Boolean isVegetarian, Boolean isLactoseFree, Boolean isGlutenFree, Profile profile, Double prepTime) {
		super();
//		this.recipeId = recipeId;
		this.recipeName = recipeName;
		this.recipeIngredients = recipeIngredients;
		this.recipeInstructions = recipeInstructions;
		this.cusineType = cusineType;
		this.isVegan = isVegan;
		this.isVegetarian = isVegetarian;
		this.isLactoseFree = isLactoseFree;
		this.isGlutenFree = isGlutenFree;
		this.profile = profile;
		this.prepTime = prepTime;
	}

	/**
	 * @return the recipeId
	 */
	public Long getRecipeId() {
		return recipeId;
	}
	/**
	 * @param recipeId the recipeId to set
	 */
	public void setRecipeId(Long recipeId) {
		this.recipeId = recipeId;
	}
	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}
	/**
	 * @param recipeName the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	/**
	 * @return the recipeIngredients
	 */
	public String getRecipeIngredients() {
		return recipeIngredients;
	}
	/**
	 * @param recipeIngredients the recipeIngredients to set
	 */
	public void setRecipeIngredients(String recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
	}
	/**
	 * @return the recipeInstructions
	 */
	public String getRecipeInstructions() {
		return recipeInstructions;
	}
	/**
	 * @param recipeInstructions the recipeInstructions to set
	 */
	public void setRecipeInstructions(String recipeInstructions) {
		this.recipeInstructions = recipeInstructions;
	}
	/**
	 * @return the cusineType
	 */
	public String getCusineType() {
		return cusineType;
	}
	/**
	 * @param cusineType the cusineType to set
	 */
	public void setCusineType(String cusineType) {
		this.cusineType = cusineType;
	}
	/**
	 * @return the isVegan
	 */
	public Boolean getIsVegan() {
		return isVegan;
	}
	/**
	 * @param isVegan the isVegan to set
	 */
	public void setIsVegan(Boolean isVegan) {
		this.isVegan = isVegan;
	}
	/**
	 * @return the isVegetarian
	 */
	public Boolean getIsVegetarian() {
		return isVegetarian;
	}
	/**
	 * @param isVegetarian the isVegetarian to set
	 */
	public void setIsVegetarian(Boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}
	/**
	 * @return the isLactoseFree
	 */
	public Boolean getIsLactoseFree() {
		return isLactoseFree;
	}
	/**
	 * @param isLactoseFree the isLactoseFree to set
	 */
	public void setIsLactoseFree(Boolean isLactoseFree) {
		this.isLactoseFree = isLactoseFree;
	}
	/**
	 * @return the isGlutenFree
	 */
	public Boolean getIsGlutenFree() {
		return isGlutenFree;
	}
	/**
	 * @param isGlutenFree the isGlutenFree to set
	 */
	public void setIsGlutenFree(Boolean isGlutenFree) {
		this.isGlutenFree = isGlutenFree;
	}
	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	/**
	 * @return the recipeId
	 */
	public Double getPrepTime() {
		return prepTime;
	}
	/**
	 * @param recipeId the recipeId to set
	 */
	public void setPrepTime(Double prepTime) {
		this.prepTime = prepTime;
	}
	
	
	public Recipe(RecipeEntity re) {
		this(re.getRecipeName(), re.getRecipeIngredients(), re.getRecipeInstructions(), re.getCusineType(),
				re.getIsVegan(), re.getIsVegetarian(), re.getIsLactoseFree(), re.getIsGlutenFree(), 
				new Profile(re.getProfile()), re.getPrepTime());
	}
	
}
