package com.spring5.recipe.service;

import com.spring5.recipe.commands.IngredientCommand;

public interface IngredientService{
	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand ingredientCommand);
	
	void deleteById(Long reipeId,Long idToDelete);
}
