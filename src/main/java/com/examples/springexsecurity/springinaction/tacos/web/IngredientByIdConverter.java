package com.examples.springexsecurity.springinaction.tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient1> {

  private IngredientRepository ingredientRepo;

  @Autowired
  public IngredientByIdConverter(IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @Override
  public Ingredient1 convert(String id) {
    return ingredientRepo.findById(id).orElse(null);
  }

}
