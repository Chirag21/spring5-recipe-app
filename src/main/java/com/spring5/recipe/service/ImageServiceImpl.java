package com.spring5.recipe.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.recipe.domain.Recipe;
import com.spring5.recipe.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImage(Long reicpeId, MultipartFile file) {
		Recipe recipe = recipeRepository.findById(reicpeId).get();
		try {
			recipe.setImage(file.getBytes());
		} catch (IOException e) {
			log.error("Error occurred while saving the image");
			e.printStackTrace();
			e.printStackTrace();
		}
		recipeRepository.save(recipe);
	}

}
