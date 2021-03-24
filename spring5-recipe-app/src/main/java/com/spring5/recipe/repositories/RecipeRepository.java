package com.spring5.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.spring5.recipe.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
}
