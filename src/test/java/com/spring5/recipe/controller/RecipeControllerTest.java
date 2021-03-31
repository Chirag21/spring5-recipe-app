package com.spring5.recipe.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.service.RecipeService;

class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController recipeController;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
	}

	@Test
	void testShowById() {
		Recipe recipe = new Recipe();
		recipe.setId(1L);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(recipe);

		try {
			mockMvc.perform(get("/recipe/show/1"))
				.andExpect(status().isOk())
				.andExpect(view()
				.name("recipe/show"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
