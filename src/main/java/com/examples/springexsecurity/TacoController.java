package com.examples.springexsecurity.springinaction.tacos.web.api;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.dao.EmptyResultDataAccessException;

import com.examples.springexsecurity.springinaction.tacos.Taco;
import com.examples.springexsecurity.springinaction.tacos.data.TacoRepository;
import com.examples.springexsecurity.springinaction.tacos.TacoOrder;


@RestController
@RequestMapping(path="/api/tacos", produces="application/json")
//@CrossOrigin(origins="http://localhost:8081")
@CrossOrigin
public class TacoController {

 private TacoRepository tacoRepo;
 
 public TacoController(
          TacoRepository tacoRepo){
  
     this.tacoRepo = tacoRepo;

 }

  @GetMapping(params="recent")
  public Iterable<Taco> recentTacos(){
  
  System.out.println("---< TacoController.recent >---");

    PageRequest page = 
        PageRequest.of(0, 12, 
          Sort.by("createdAt")
           .descending() );

    return 
      tacoRepo.findAll(page).getContent();

  }
  @GetMapping("/{id}")
  public ResponseEntity<Taco> tacoById(
             @PathVariable Long id){
    
   System.out.println("Get by Id ");
    Optional<Taco> opt =
                tacoRepo.findById(id);

    if(opt.isPresent()){
     return new 
      ResponseEntity<Taco>(opt.get(), 
                        HttpStatus.OK);
    }else{
      return new ResponseEntity<>(null,
                   HttpStatus.NOT_FOUND);
    }

  }

@PostMapping(consumes="application/json")
@ResponseStatus(HttpStatus.CREATED)
public Taco postTaco(@RequestBody 
                        Taco taco){

  System.out.println("---< postTaco >---");
  
    return tacoRepo.save(taco);
 }

 @PutMapping(path="/{orderId}", consumes="application/json")
 public Taco putTaco(
    @PathVariable("orderId") Long orderId,
     @RequestBody Taco taco){

  System.out.println("<<< Put by id >>>"); 
   taco.setId(orderId);
   return tacoRepo.save(taco);
 }

 @PatchMapping(path="/{orderId}", consumes="application/json")
 public Taco patchTaco(
    @PathVariable("orderId") Long orderId,
    @RequestBody Taco patchTaco) {

  Taco taco = 
      tacoRepo.findById(orderId).get();

  if(patchTaco.getName() != null){
      taco.setName(patchTaco.getName() );
  }
  if(patchTaco.getCreatedAt() != null){
   taco.setCreatedAt(patchTaco.getCreatedAt() );
  }
  if(patchTaco.getIngredients() != null){
    taco.setIngredients(patchTaco.getIngredients() );
  }
  return tacoRepo.save(taco);


 }
  
 @DeleteMapping("/{orderId}")
 @ResponseStatus(HttpStatus.NO_CONTENT)
 public void deleteTaco(
  @PathVariable("orderId") Long orderId){

   try {
    tacoRepo.deleteById(orderId);
   }
  catch(EmptyResultDataAccessException e){
   System.out.println("Err: "+e.getMessage()); 
   }

  }



}




