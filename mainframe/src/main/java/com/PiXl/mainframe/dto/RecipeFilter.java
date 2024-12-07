package com.PiXl.mainframe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeFilter {
    private Boolean isGlutenFree;
    private Boolean isVegan;
    private Boolean isVegetarian;
    private Boolean isLactoseFree;

    // Constructor, getters, and setters
    public RecipeFilter(Boolean isGlutenFree, Boolean isVegan, Boolean isVegetarian, Boolean isLactoseFree) {
        this.isGlutenFree = isGlutenFree;
        this.isVegan = isVegan;
        this.isVegetarian = isVegetarian;
        this.isLactoseFree = isLactoseFree;
    }
}
