package com.examples.springexsecurity;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1;

import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;

@SpringBootApplication
//@ComponentScan(basePackages = "com.examples.springexsecurity")
//@ComponentScan(basePackageClasses =com.examples.springexsecurity.HomeController.class)
public class SpringexsecurityApplication {

  public static void main(String[] args) {
	SpringApplication.run(SpringexsecurityApplication.class, args);
	}

  /*@Bean
  public CommandLineRunner dataLoader(
                  IngredientRepository repo) {
    
      return new CommandLineRunner() {
      @Override
      public void run(String... args) throws Exception {
        repo.save(new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP));
        repo.save(new Ingredient1("COTO", "Corn Tortilla", Type.WRAP));
       repo.save(new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN));
        repo.save(new Ingredient1("CARN", "Carnitas", Type.PROTEIN));
        repo.save(new Ingredient1("TMTO", "Diced Tomatoes", Type.VEGGIES));
         repo.save(new Ingredient1("LETC", "Lettuce", Type.VEGGIES));
         repo.save(new Ingredient1("CHED", "Cheddar", Type.CHEESE));
        repo.save(new Ingredient1("JACK", "Monterrey Jack", Type.CHEESE));
        repo.save(new Ingredient1("SLSA", "Salsa", Type.SAUCE));
        repo.save(new Ingredient1("SRCR", "Sour Cream", Type.SAUCE));
      }
    };
  }*/

}
