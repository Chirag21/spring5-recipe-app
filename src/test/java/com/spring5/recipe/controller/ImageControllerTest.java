package com.spring5.recipe.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.service.ImageService;
import com.spring5.recipe.service.RecipeService;

class ImageControllerTest {
	
	@Mock
	ImageService imageSerivce;
	
	@Mock
	RecipeService recipeService;
	
	ImageController imageController;
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageController = new ImageController(imageSerivce, recipeService);
		mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
	}

	@Test
	void getImageForm() {
		//given
		RecipeCommand recipeCommand = new RecipeCommand();
		recipeCommand.setId(1L);
		
		when(recipeService.findRecipeCommandById(ArgumentMatchers.anyLong())).thenReturn(recipeCommand);
		
		//then
		try {
			mockMvc.perform(get("/recipe/1/image"))
				.andExpect(status().isOk())
				.andExpect(model().attributeExists("recipe"))
				.andExpect(view().name("recipe/imageUploadForm"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(recipeService,times(1)).findRecipeCommandById(ArgumentMatchers.anyLong());
	}

	
	@Test
	void handleImagePostTest() {
		MockMultipartFile multipartFile
					= new MockMultipartFile("imagefile", "testing.txt", "text/plain", "file post".getBytes());
		
		try {
			mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
					.andExpect(status().is3xxRedirection())
					.andExpect(header().string("Location", "/recipe/1/show"))
					.andExpect(view().name("redirect:/recipe/1/show"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		verify(imageSerivce,times(1)).saveImage(ArgumentMatchers.anyLong(),ArgumentMatchers.any());
	}
}
