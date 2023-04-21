package com.examples.springexsecurity.springinaction.tacos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1; 

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  @Size(min=5, message="Name must be at least 5 characters long")
  private String name;

  private Date createdAt = new Date();

  @Size(min=1, message="You must choose at least 1 ingredient")
  @ManyToMany()
  private List<Ingredient1> ingredients = new ArrayList<>();
  
  public void addIngredient(Ingredient1 ingredient) {
    this.ingredients.add(ingredient);
  }

}
