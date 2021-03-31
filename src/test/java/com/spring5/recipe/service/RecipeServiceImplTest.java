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

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.repositories.RecipeRepository;

class RecipeServiceImplTest {
	RecipeServiceImpl recipeService;

	@Mock
	RecipeRepository recipeRepository;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void testGetRecipes() {
		Recipe recipe = new Recipe();
		HashSet<Recipe> recipeData = new HashSet<>();
		recipeData.add(recipe);
		when(recipeService.getRecipes()).thenReturn(recipeData);
		Set<Recipe> recipes = recipeService.getRecipes();
		assertEquals(1, recipes.size());
		verify(recipeRepository, times(1)).findAll(); // times(1) is default
		verify(recipeRepository, never()).findById(ArgumentMatchers.anyLong());
	}

	@Test
	void getRecipeByIdTest() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);

		Recipe recipeReturned = recipeService.findById(1L);
		assertNotNull("Null recipe returned!!", recipeReturned);
		verify(recipeRepository, times(1)).findById(1L);
		verify(recipeRepository, never()).findAll();
	}

}
