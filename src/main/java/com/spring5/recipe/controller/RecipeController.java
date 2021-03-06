package com.spring5.recipe.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.exceptions.RecipeNotFoundException;
import com.spring5.recipe.service.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RecipeController {

	private static final String RECIPE = "recipe";
	private final RecipeService recipeService;

	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/show")
	public String showById(@PathVariable String id, Model model) {
		model.addAttribute(RECIPE, recipeService.findById(Long.valueOf(id)));
		return "recipe/show";
	}
	
	@GetMapping("recipe/new")
	public String newRecipe(Model model) {
		model.addAttribute(RECIPE,new RecipeCommand());
		return "recipe/recipeForm";
	}
	
	@GetMapping("recipe/{id}/update")
	public String updateRecipe(@PathVariable String id, Model model) {
		model.addAttribute(RECIPE,recipeService.findRecipeCommandById(Long.valueOf(id)));
		return "recipe/recipeForm";
	}
	
	@PostMapping("recipe")
	public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
		
		RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/" + savedCommand.getId() + "/show";
	}
	
	
	@GetMapping("/recipe/{recipeId}/delete")
	public String deleteRecipeById(@PathVariable Long recipeId,Model model) {
		log.debug("Deleting recipe id:" + recipeId);
		recipeService.deleteById(recipeId);
		return "redirect:/index";
	}
	
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(RecipeNotFoundException.class)
	public ModelAndView handleNotHound(Exception e) {
		log.error("Handling not found exception.");
		log.error(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("404Error");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
	
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView handleNumberFormatException(Exception e) {
		log.error("Handling number format exception.");
		log.error(e.getMessage());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("400Error");
		modelAndView.addObject("exception", e);
		return modelAndView;
	}
}