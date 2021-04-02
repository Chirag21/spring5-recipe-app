package com.spring5.recipe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring5.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {
	private final RecipeService recipeService;

	public IndexController(RecipeService recipeService) {
		this.recipeService = recipeService;
		log.debug("In IndexController constructor");

	}

	@RequestMapping({ "", "/", "/index", "/index.html" })
	public String getIndexPage(Model model) {
		log.debug("In getIndexPage method of IndexController");
		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}
