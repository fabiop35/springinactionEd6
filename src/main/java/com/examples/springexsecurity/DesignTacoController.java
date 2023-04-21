package com.examples.springexsecurity.springinaction.tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.examples.springexsecurity.springinaction.tacos.Ingredient1;
import com.examples.springexsecurity.springinaction.tacos.Ingredient1.Type;
import com.examples.springexsecurity.springinaction.tacos.TacoOrder;
import com.examples.springexsecurity.springinaction.tacos.Taco;
import com.examples.springexsecurity.springinaction.tacos.data.IngredientRepository;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

  private final IngredientRepository ingredientRepo;

  @Autowired
  public DesignTacoController(
        IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient1> ingredients = new ArrayList<>();
    ingredientRepo.findAll().forEach(i -> ingredients.add(i));

    Type[] types = Ingredient1.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    System.out.println("》》》DesignTacoController.design.showDesignForm.GET:   "+java.time.LocalTime.now());
    return "design";
  }

  @PostMapping
  public String processTaco(
      @Valid Taco taco, Errors errors,
      @ModelAttribute TacoOrder tacoOrder) {
    
    System.out.println("》》》DesingTacoController.POST: "+java.time.LocalTime.now());

    if (errors.hasErrors()) {
      System.out.println("===>errors: "+errors);  
      return "design";
    }

    tacoOrder.addTaco(taco);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient1> filterByType(
      List<Ingredient1> ingredients, Type type) {
    return ingredients
              .stream()
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList());
  }

}
