package com.spring5.recipe.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.repositories.RecipeRepository;

class ImageServiceImplTest {

	@Mock
	RecipeRepository recipeRepository;

	ImageService imageService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		imageService = new ImageServiceImpl(recipeRepository);
	}

	@Test
	void testSaveImage() throws Exception{
		MultipartFile file = new MockMultipartFile("iamgefile", "testing.txt", "text/plain", "test file".getBytes());
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> recipeOptional = Optional.of(recipe);
		
		when(recipeRepository.findById(ArgumentMatchers.anyLong())).thenReturn(recipeOptional);
		
		//when
		imageService.saveImage(1L, file);
		
		//then
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(recipeRepository, times(1)).save(argumentCaptor.capture());
        Recipe savedRecipe = argumentCaptor.getValue();
        assertEquals(file.getBytes().length, savedRecipe.getImage().length);
	}

}
