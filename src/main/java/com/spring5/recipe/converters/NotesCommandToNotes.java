package com.spring5.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.spring5.recipe.commands.NotesCommand;
import com.spring5.recipe.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand, Notes> {

   @Synchronized
   @Nullable
   @Override
   public Notes convert(NotesCommand source) {
       if(source == null) {
           return null;
       }

       final Notes notes = new Notes();
       notes.setId(source.getId());
       notes.setRecipeNotes(source.getRecipeNotes());
       return notes;
   }
}
