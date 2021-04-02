package com.spring5.recipe.service;

import java.util.Set;

import com.spring5.recipe.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();

}
