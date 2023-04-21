package com.examples.springexsecurity.springinaction.tacos;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;
import com.examples.springexsecurity.springinaction.    tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.data.OrderRepository;
import com.examples.springexsecurity.springinaction.tacos.web.DesignTacoController;
import com.examples.springexsecurity.springinaction.tacos.Taco;
@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<Ingredient1> ingredients;

  private Taco design;

  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private OrderRepository orderRepository;

  @BeforeEach
  public void setup() {
    ingredients = Arrays.asList(
      new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP),
      new Ingredient1("COTO", "Corn Tortilla", Type.WRAP),
      new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN),
      new Ingredient1("CARN", "Carnitas", Type.PROTEIN),
      new Ingredient1("TMTO", "Diced Tomatoes", Type.VEGGIES),
      new Ingredient1("LETC", "Lettuce", Type.VEGGIES),
      new Ingredient1("CHED", "Cheddar", Type.CHEESE),
      new Ingredient1("JACK", "Monterrey Jack", Type.CHEESE),
      new Ingredient1("SLSA", "Salsa", Type.SAUCE),
      new Ingredient1("SRCR", "Sour Cream", Type.SAUCE)
    );

    when(ingredientRepository.findAll())
        .thenReturn(ingredients);

    when(ingredientRepository.findById("FLTO")).thenReturn(Optional.of(new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP)));
    when(ingredientRepository.findById("GRBF")).thenReturn(Optional.of(new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN)));
    when(ingredientRepository.findById("CHED")).thenReturn(Optional.of(new Ingredient1("CHED", "Cheddar", Type.CHEESE)));
    design = new Taco();
    design.setName("Test Taco");

    design.setIngredients(
        Arrays.asList(
            new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient1("CHED", "Cheddar", Type.CHEESE)));

  }

  @Test
  public void testShowDesignForm() throws Exception {
    mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(model().attribute("wrap", ingredients.subList(0, 2)))
        .andExpect(model().attribute("protein", ingredients.subList(2, 4)))
        .andExpect(model().attribute("veggies", ingredients.subList(4, 6)))
        .andExpect(model().attribute("cheese", ingredients.subList(6, 8)))
        .andExpect(model().attribute("sauce", ingredients.subList(8, 10)));
  }

  @Test
  public void processTaco() throws Exception {
    mockMvc.perform(post("/design")
        .content("name=Test+Taco&ingredients=FLTO,GRBF,CHED")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header().stringValues("Location", "/orders/current"));
  }

}
