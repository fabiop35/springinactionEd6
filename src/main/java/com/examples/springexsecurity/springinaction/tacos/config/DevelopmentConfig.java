package com.examples.springexsecurity.springinaction.tacos.config;

import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.examples.springexsecurity.springinaction.tacos.data.User;
import com.examples.springexsecurity.springinaction.tacos.Taco;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;
import com.examples.springexsecurity.springinaction.tacos.data.TacoRepository;
import com.examples.springexsecurity.springinaction.restclient.TacoRestClient;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {

  @Autowired
  private TacoRestClient tacoClient;

  @Bean
  public CommandLineRunner dataLoader(
    IngredientRepository ingredientRepo,
    UserRepository userRepo,
    TacoRepository tacoRepo,
    PasswordEncoder encoder
    ) {

    return new CommandLineRunner() {
      @Override
      public void run(String... args) 
                    throws Exception {

    ingredientRepo.deleteAll();
    userRepo.deleteAll();
    tacoRepo.deleteAll();

    ingredientRepo.save(new 
         Ingredient1("FLTO", "Flour Tortilla", Type.WRAP));
     ingredientRepo.save(new Ingredient1("COTO", "Corn Tortilla", Type.WRAP));
    ingredientRepo.save(new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN));
    ingredientRepo.save(new Ingredient1("CARN", "Carnitas", Type.PROTEIN));
    ingredientRepo.save(new Ingredient1("TMTO", "Diced Tomatoes", Type.VEGGIES));
    ingredientRepo.save(new Ingredient1("LETC", "Lettuce", Type.VEGGIES));
    ingredientRepo.save(new Ingredient1("CHED", "Cheddar", Type.CHEESE));
    ingredientRepo.save(new Ingredient1("JACK", "Monterrey Jack", Type.CHEESE));
    ingredientRepo.save(new Ingredient1("SLSA", "Salsa", Type.SAUCE));
    ingredientRepo.save(new Ingredient1("SRCR", "Sour Cream", Type.SAUCE));
                
 userRepo.save( new User("anibal", 
    encoder.encode("123"), "Craig Walls",
    "123 North Street", "Cross Roads", 
    "TX", "76227", "123-123-1234") );
   
  Ingredient1 flourTortilla =
    new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP); 
  Ingredient1 cornTortilla = 
     new Ingredient1("COTO", "Corn Tortilla", Type.WRAP); 
 Ingredient1 groundBeef = new Ingredient1(
"GRBF", "Ground Beef", Type.PROTEIN);

 Ingredient1 carnitas = new Ingredient1(
"CARN", "Carnitas", Type.PROTEIN);

Ingredient1 tomatoes = new Ingredient1(
"TMTO", "Diced Tomatoes", Type.VEGGIES);

 Ingredient1 lettuce = new Ingredient1(
"LETC", "Lettuce", Type.VEGGIES); 

 Ingredient1 cheddar = new Ingredient1(
"CHED", "Cheddar", Type.CHEESE);

 Ingredient1 jack = new Ingredient1(
"JACK", "Monterrey Jack", Type.CHEESE);

 Ingredient1 salsa = new Ingredient1(
"SLSA", "Salsa", Type.SAUCE);
Ingredient1 sourCream = 
    new Ingredient1("SRCR", "Sour Cream",
            Type.SAUCE); 
  ingredientRepo.save(flourTortilla); 
  ingredientRepo.save(cornTortilla); 
  ingredientRepo.save(groundBeef); 
  ingredientRepo.save(carnitas); 
  ingredientRepo.save(tomatoes);
  
  Taco taco1 = new Taco(); 
  taco1.setName("Carnivore"); 
  taco1.setIngredients(Arrays.asList(
  flourTortilla, groundBeef, carnitas, 
  sourCream, salsa, cheddar)); 
  tacoRepo.save(taco1);

  Taco taco3 = new Taco();
  taco3.setName("Veg-Out");
  taco3.setIngredients(Arrays.asList(
  flourTortilla, cornTortilla, tomatoes,
  lettuce, salsa));
  tacoRepo.save(taco3);

  /* Rest Taco Client */ 
  //GET
  System.out.println("XXXXXXXXXXXX > Taco Rest Client < XXXXXXXXXXXXX");
  //COMMENTED AFTER ENABLED AUTH
 //System.out.println("》Taco:  " + tacoClient.getTacoById("2")); 
  //PUT
  System.out.println("》》》PUT 《《《");
  Taco taco = new Taco();
  long lId = 2;
  taco.setId(lId);
  taco.setName("PutModified");
  Date createdAt = new Date();
  taco.setCreatedAt(createdAt);
  List<Ingredient1> ingredients = 
                    new ArrayList<>();
  //Ingredient1 salsa = 
   // new Ingredient1("SLSA", "Salsa", Type.SAUCE);
  ingredients.add(salsa);
  taco.setIngredients(ingredients);    
  //COMMENTED AFTER ENABLED AUTH
  //tacoClient.updateTaco(taco);

  //DELETE
  //COMMENTED AFTER ENABLED AUTH
  //tacoClient.deleteTaco(taco);
  //POST
  System.out.println("》》》POST 《《《");
  Taco tacoPost = new Taco();
  //long lId = 2;
  //taco.setId(lId);
  tacoPost.setName("PostCreated");
  tacoPost.setCreatedAt(createdAt);
  tacoPost.setIngredients(ingredients);
  //COMMENTED AFTER ENABLED AUTH
  //System.out.println("Taco Created: "
  //      +tacoClient.createTaco(tacoPost));
      }
    };
  }
  
}
