package com.examples.springexsecurity.springinaction.tacos;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;
import com.examples.springexsecurity.springinaction.tacos.data.OrderRepository;
import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc;
  
  @MockBean
  private IngredientRepository ingredientRepository;

  @MockBean
  private OrderRepository orderRepository;

  @MockBean
  UserRepository userRepository;

  //ToDo @Test
  public void testHomePage() throws Exception {
    System.out.println("####### testHomePage");
    mockMvc.perform(get("/"))
    .andExpect(
       //status().isUnauthorized() 
       status().isOk()
       

      //.andExpect(status().isOk())
      //.andExpect(view().name("home"))
      // .andExpect(content().string(
     //      containsString("Welcome to..."))
    );  
  }

}
