package com.spring5.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.recipe.service.ImageService;
import com.spring5.recipe.service.RecipeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ImageController {
	private final ImageService imageService;
	private final RecipeService recipeService;
	
	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findRecipeCommandById(Long.valueOf(id)));
		return "recipe/imageUploadForm";
	}
	
	@PostMapping("recipe/{id}/image")
	public String handleImagePost(@PathVariable String id,@RequestParam("imagefile") MultipartFile file) {
		imageService.saveImage(Long.valueOf(0), file);
		return "redirect:/recipe/" + id + "/show";
	}
}
