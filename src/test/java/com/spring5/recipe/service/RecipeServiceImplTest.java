package com.spring5.recipe.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring5.recipe.converters.RecipeCommandToRecipe;
import com.spring5.recipe.converters.RecipeToRecipeCommand;
import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.exceptions.RecipeNotFoundException;
import com.spring5.recipe.repositories.RecipeRepository;

class RecipeServiceImplTest {
	RecipeService recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@Mock
	RecipeToRecipeCommand recipeToRecipeCommand;

	@Mock
	RecipeCommandToRecipe recipeCommandToRecipe;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
	}

	@Test
	void getRecipeByIdTest() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);

		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);

		Recipe recipeReturned = recipeService.findById(1L);

		assertNotNull("Null recipe returned", recipeReturned);
		verify(recipeRepository, times(1)).findById(ArgumentMatchers.anyLong());
		verify(recipeRepository, never()).findAll();
	}

	@Test
	void getRecipesTest() throws Exception {

		Recipe recipe = new Recipe();
		HashSet<Recipe> receipesData = new HashSet<>();
		receipesData.add(recipe);

		when(recipeService.getRecipes()).thenReturn(receipesData);

		Set<Recipe> recipes = recipeService.getRecipes();

		assertEquals(recipes.size(), 1);
		verify(recipeRepository, times(1)).findAll();
		verify(recipeRepository, never()).findById(ArgumentMatchers.anyLong());
	}
	
	@org.junit.Test(expected=RecipeNotFoundException.class)
	void getRecipeByIdNotFoundTest() {
		Optional<Recipe> recipeOptional = Optional.empty();
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional.get());
		recipeService.findById(1L);
	}

}
