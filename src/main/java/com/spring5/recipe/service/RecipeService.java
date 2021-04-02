package com.spring5.recipe.service;

import java.util.Set;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.domain.Recipe;

public interface RecipeService {
	
	Set<Recipe> getRecipes();

	Recipe findById(Long id);
	
	RecipeCommand findRecipeCommandById(Long id);
	
	RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	
	void deleteById(Long idToDelete);
}
