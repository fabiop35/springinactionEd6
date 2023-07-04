package com.examples.springexsecurity.springinaction.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1;

import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository 
         extends CrudRepository<Ingredient1, String> {
  
}
