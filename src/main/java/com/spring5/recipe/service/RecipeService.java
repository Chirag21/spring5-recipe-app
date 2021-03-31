package com.spring5.recipe.service;

import java.util.Set;

import com.spring5.recipe.domain.Recipe;

public interface RecipeService {
	Set<Recipe> getRecipes();

	Recipe findById(Long id);
}
