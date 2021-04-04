package com.spring5.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring5.recipe.commands.RecipeCommand;
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
	public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {
		imageService.saveImage(Long.valueOf(id), file);
		return "redirect:/recipe/" + id + "/show";
	}

	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDB(@PathVariable String id, HttpServletResponse response) {
		RecipeCommand recipeCommand = recipeService.findRecipeCommandById(Long.valueOf(id));
		byte[] imageByteArray = recipeCommand.getImage();
		if (Objects.nonNull(imageByteArray))
			response.setContentType("image/jpeg");
		InputStream is = new ByteArrayInputStream(imageByteArray);
		try {
			IOUtils.copy(is, response.getOutputStream());
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
