package com.spring5.recipe.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.service.RecipeService;

class RecipeControllerTest {

	@Mock
	RecipeService recipeService;

	RecipeController recipeController;
	
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		recipeController = new RecipeController(recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
	}
	
    @Test
    void testGetRecipeById() throws Exception {

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(ArgumentMatchers.anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {
        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }
    
	@Test
	void testPostNewRecipeForm() throws Exception {
		RecipeCommand command = new RecipeCommand();
		command.setId(2L);

		when(recipeService.saveRecipeCommand(ArgumentMatchers.any())).thenReturn(command);

		mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("id", "")
//                .param("description", "some string")
				).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/recipe/2/show"));
	}
 
	
	@Test
    void testGetUpdateView() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId(2L);

        when(recipeService.findRecipeCommandById(ArgumentMatchers.anyLong())).thenReturn(command);

        mockMvc.perform(get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

	@Test
	void testDeleteById() {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
		try {
			mockMvc.perform(get("/recipe/2/delete"))
				.andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/index"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(recipeService,times(1)).deleteById(ArgumentMatchers.anyLong());
	}
}
