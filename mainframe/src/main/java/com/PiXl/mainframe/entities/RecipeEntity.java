package com.PiXl.mainframe.entities;

import java.util.Objects;

import com.PiXl.mainframe.models.Recipe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "recipes")
public class RecipeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recipeId;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private ProfileEntity profile;
	
	@Column(name = "ingredients")
	private String recipeIngredients;
	
	@Column(name = "instructions")
	private String recipeInstructions;
	
	@Column(name = "cusineType")
	private String cusineType;
	
	@Column(name = "vegan")
	private Boolean isVegan;
	
	@Column(name = "vegetarian")
	private Boolean isVegetarian;
	
	@Column(name = "lactoseFree")
	private Boolean isLactoseFree;
	
	@Column(name = "glutenFree")
	private Boolean isGlutenFree;
	
	@Column(name = "prepTime")
	private Double prepTime;

	
	public RecipeEntity() {
		super();
	}
	public RecipeEntity(Long recipeId, ProfileEntity profile, String recipeIngredients, String recipeInstructions,
			String cusineType, Boolean isVegan, Boolean isVegetarian, Boolean isLactoseFree, Boolean isGlutenFree,
			Double prepTime) {
		super();
		this.recipeId = recipeId;
		this.profile = profile;
		this.recipeIngredients = recipeIngredients;
		this.recipeInstructions = recipeInstructions;
		this.cusineType = cusineType;
		this.isVegan = isVegan;
		this.isVegetarian = isVegetarian;
		this.isLactoseFree = isLactoseFree;
		this.isGlutenFree = isGlutenFree;
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
	public ProfileEntity getProfile() {
		return profile;
	}
	
	/**
	 * @param profile the profile to set
	 */
	public void setProfile(ProfileEntity profile) {
		this.profile = profile;
	}
	
	/**
	 * @return the prepTime
	 */
	public Double getPrepTime() {
		return prepTime;
	}
	
	/**
	 * @param prepTime the prepTime to set
	 */
	public void setPrepTime(Double prepTime) {
		this.prepTime = prepTime;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cusineType, isGlutenFree, isLactoseFree, isVegan, isVegetarian, prepTime, profile, recipeId,
				recipeIngredients, recipeInstructions);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RecipeEntity other = (RecipeEntity) obj;
		return Objects.equals(profile, other.profile) && Objects.equals(recipeId, other.recipeId);
	}
	
	public RecipeEntity(Recipe recipe) {
		this(recipe.getRecipeId(), new ProfileEntity(recipe.getProfile()), recipe.getRecipeIngredients(), recipe.getRecipeInstructions(), recipe.getCusineType(),
				recipe.getIsVegan(), recipe.getIsVegetarian(), recipe.getIsLactoseFree(), recipe.getIsGlutenFree(), recipe.getPrepTime());
	}
}
