package com.spring5.recipe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RecipeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6835393586360277600L;
	
	public RecipeNotFoundException(){
		
	}
	
	public RecipeNotFoundException(String message) {
		super(message);
	}
	
	public RecipeNotFoundException(String message, Throwable cause) {
		super(message,cause);
	}
}
