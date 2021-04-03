package com.spring5.recipe.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum Difficulty {
	EASY, MODERATE, HARD;
}
