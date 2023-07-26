package com.examples.springexsecurity.springinaction.tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseStatus;
 import org.springframework.web.bind.annotation.RestController;

 //import org.springframework.security.access.prepost.PreAuthorize;

import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1;

@RestController
@RequestMapping(path="/api/ingredients", produces="application/json")
@CrossOrigin(origins="http://localhost:8081")
public class IngredientController {

 
 private IngredientRepository 
                ingredientRepository;
 

 @Autowired
 public IngredientController(
 IngredientRepository ingredientRepository){
     this.ingredientRepository = 
                ingredientRepository;
 }

 @GetMapping
 public Iterable<Ingredient1> 
                    allIngredients(){
   return ingredientRepository.findAll();
 }
 
 @PostMapping
 @ResponseStatus(HttpStatus.CREATED)
 //@PreAuthorize("hasRole('ADMIN')")
 public Ingredient1 savaIngredient(
    @RequestBody Ingredient1 ingredient){
    
  return 
    ingredientRepository.save(ingredient);
 }

 @DeleteMapping("/{id}")
 @ResponseStatus(HttpStatus.NO_CONTENT)
 //@PreAuthorize("hasRole('USER')")
 public void deleteIngredient(
         @PathVariable String id){

  ingredientRepository.deleteById(id);
 }


}
