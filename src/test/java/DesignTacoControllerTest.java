package com.examples.springexsecurity.springinaction.tacos;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;


import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.data.OrderRepository;
import com.examples.springexsecurity.springinaction.tacos.web.DesignTacoController;
import com.examples.springexsecurity.springinaction.tacos.Taco;
import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;
import com.examples.springexsecurity.springinaction.tacos.data.TacoRepository;
import com.examples.springexsecurity.springinaction.tacos.data.User;


@ExtendWith(SpringExtension.class)
@WebMvcTest(DesignTacoController.class)
public class DesignTacoControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private List<Ingredient1> ingredients;

  private Taco taco;

  @MockBean
  UserRepository userRepository;

  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  private TacoRepository tacoRepository;

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
    taco = new Taco();
    taco.setName("Test Taco");

    taco.setIngredients(
        Arrays.asList(
            new Ingredient1("FLTO", "Flour Tortilla", Type.WRAP),
            new Ingredient1("GRBF", "Ground Beef", Type.PROTEIN),
            new Ingredient1("CHED", "Cheddar", Type.CHEESE)));

  }

  @Test
  @WithMockUser(username="testuser", password="testpass")
  public void testShowDesignForm() 
                     throws Exception {

    mockMvc.perform(get("/design"))
        .andExpect(status().isOk())
        .andExpect(view().name("design"))
        .andExpect(model().attribute("wrap", 
                    ingredients.subList(0, 2)))
        .andExpect(model().attribute("protein", 
                     ingredients.subList(2, 4)))
        .andExpect(model().attribute("veggies", 
                      ingredients.subList(4, 6)))
        .andExpect(model().attribute("cheese", 
                     ingredients.subList(6, 8)))
        .andExpect(model().attribute("sauce", 
                    ingredients.subList(8, 10)));
  }

  @Test
  @WithMockUser(username="testuser", password="testpass", authorities="ROLE_USER")
  public void processTaco() 
               throws Exception {

    //Register
    /* mockMvc.perform(
      post("/register").with(csrf() ) 
     .param("username", "anibal")
     .param("password", "123")
     .param("confirm", "123" ) )
        .andExpect( view().name("/login") ); */


   //
  when( tacoRepository.save(taco) )
                    .thenReturn(taco);

    when(
      userRepository.findByUsername("testuser")
     )
	.thenReturn(
        new User("testuser", "testpass", "Test User", "123 Street", "Someville", "CO", "12345", "123-123-1234")
    );

   mockMvc.perform(
      post("/design").with(csrf() )
      .content(
    "name=Test+Taco&ingredients=FLTO,GRBF,CHED")
      .contentType(
        MediaType.APPLICATION_FORM_URLENCODED))
        .andExpect(status().is3xxRedirection())
        .andExpect(header()
        .stringValues("Location", 
                    "/orders/current"));


  }

}
