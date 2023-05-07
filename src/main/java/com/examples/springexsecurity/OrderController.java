package com.examples.springexsecurity.springinaction.tacos.web;

import java.time.LocalDateTime;  
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;

import com.examples.springexsecurity.springinaction.tacos.TacoOrder;
import com.examples.springexsecurity.springinaction.tacos.data.OrderRepository;

import com.examples.springexsecurity.springinaction.tacos.data.User;

@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@ConfigurationProperties(prefix="taco.orders")
public class OrderController {

  /*private int pageSize = 3;

  public void setPageSize(int pageSize){
      this.pageSize = pageSize;
  } */
  
  private OrderProperties props;
  
  //public OrderController(){}

  private OrderRepository orderRepo;

  public OrderController(
           OrderRepository orderRepo,
               OrderProperties props){
    
      this.orderRepo = orderRepo;
      this.props = props;
  }

 /* public OrderController(OrderRepository
                            orderRepo) {
     this.orderRepo = orderRepo;
  }*/

  @GetMapping("/current")
  public String orderForm() {
    System.out.println("》》》OrderController.orderForm:  "+java.time.LocalTime.now() );
    return "orderForm";
  }
  
  @PreAuthorize("hasRole('USER')")
  @GetMapping("/getOrders")
  public String getOrders(Model model){
    System.out.println("》》》OrderController.getOrders:  "+java.time.LocalTime.now() );

    model.addAttribute("orders", 
               orderRepo.findAll());
    
    return "getOrders";
  }

  @GetMapping
  public String ordersForUser(
          @AuthenticationPrincipal 
                            User user,
                            Model model){
   
    Pageable pageable = 
         PageRequest.of(0, props.getPageSize() );


    model.addAttribute("orders", 
     orderRepo.
     findByUserOrderByPlacedAtDesc(
                    user, pageable));
     System.out.println("### OrderController.ordersForUser.username: "+
             user.getUsername() );

     return "orderList";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus) {
    if (errors.hasErrors()) {
      return "orderForm";
    }

    orderRepo.save(order);
    sessionStatus.setComplete();

    return "redirect:/";
  }

}
