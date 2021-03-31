package com.spring5.recipe.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Set<Recipe> getRecipes() {
		log.debug("Inside getRecipes of RecipeServiceImpl");
		Set<Recipe> recipeSet = new HashSet<>();
		// recipeRepository.findAll().forEach(recipeSet::add);
		recipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet;
	}

	@Override
	public Recipe findById(Long id) {
		Optional<Recipe> recipeOptional = recipeRepository.findById(id);
		if (!recipeOptional.isPresent())
			throw new RuntimeException("Recipe not found with id = " + id);
		else
			return recipeOptional.get();
	}
}
