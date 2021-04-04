package com.spring5.recipe.converters;

import org.hibernate.query.criteria.internal.predicate.IsEmptyPredicate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.spring5.recipe.commands.RecipeCommand;
import com.spring5.recipe.domain.Recipe;

import lombok.Synchronized;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe>{

	private final CategoryCommandToCategory categoryConveter;
	private final IngredientCommandToIngredient ingredientConverter;
	private final NotesCommandToNotes notesConverter;

	public RecipeCommandToRecipe(CategoryCommandToCategory categoryConveter,
			IngredientCommandToIngredient ingredientConverter, NotesCommandToNotes notesConverter) {
		this.categoryConveter = categoryConveter;
		this.ingredientConverter = ingredientConverter;
		this.notesConverter = notesConverter;
	}

	@Override
	@Synchronized
	@Nullable
	public Recipe convert(RecipeCommand source) {
		if (source == null) {
			return null;
		}

		final Recipe recipe = new Recipe();
		recipe.setId(source.getId());
		recipe.setCookTime(source.getCookTime());
		recipe.setPrepTime(source.getPrepTime());
		recipe.setDescription(source.getDescription());
		recipe.setDifficulty(source.getDifficulty());
		recipe.setDirections(source.getDirections());
		recipe.setServings(source.getServings());
		recipe.setSource(source.getSource());
		recipe.setUrl(source.getUrl());
		recipe.setImage(source.getImage());
		recipe.setNotes(notesConverter.convert(source.getNotes()));

		if (source.getCategories() != null && !source.getCategories().isEmpty()) {
			source.getCategories().forEach(category -> recipe.getCategories().add(categoryConveter.convert(category)));
		}

		if (source.getIngredients() != null && !source.getIngredients().isEmpty()) {
			source.getIngredients()
					.forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
		}

		return recipe;
	}
}
