package com.examples.springexsecurity.springinaction.tacos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.examples.springexsecurity.springinaction.tacos.data.User;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Bean
  public CommandLineRunner dataLoader(
           IngredientRepository repo,
              UserRepository userRepo, 
             PasswordEncoder encoder) {

    return new CommandLineRunner() {
      @Override
      public void run(String... args) 
                    throws Exception {

    	repo.deleteAll();
        userRepo.deleteAll();

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
                
    userRepo.save(
      new User("anibal", 
          encoder.encode("123"), 
            "Craig Walls", "123 North Street", "Cross Roads", "TX", 
            "76227", "123-123-1234"));
      }
    };
  }
  
}
