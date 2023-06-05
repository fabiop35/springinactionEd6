package com.examples.springexsecurity.springinaction.restclient;

import java.net.URI;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;

import lombok.extern.slf4j.Slf4j;

import com.examples.springexsecurity.springinaction.tacos.Taco;

@Slf4j
@Service
public class TacoRestClient{

 @Autowired
 private RestTemplate restTemplate;
 
 @Bean
 public RestTemplate restTemplate(){
   return new RestTemplate();
 }

 public Taco getTacoById(String tacoId){

   ResponseEntity<Taco> responseEntity =
        restTemplate.getForEntity(
    "http://localhost:8081/api/tacos/{id}",
    Taco.class, tacoId);

   System.out.println("Feched time:{}"+
             responseEntity.getHeaders()
                               .getDate());
   return responseEntity.getBody();
  
   /*Map<String, String> urlVariables =
                          new HashMap<>();
   urlVariables.put("id", tacoId);
   URI url = UriComponentsBuilder
    .fromHttpUrl(
    "http://localhost:8081/api/tacos/{id}").
    build(urlVariables);
    
    return restTemplate
         .getForObject(url, Taco.class); */


   /*return restTemplate.getForObject(
   "http://localhost:8081/api/tacos/{id}",
    Taco.class, tacoId)*/
   /* return restTemplate.getForObject(
    "http://localhost:8081/api/tacos/{id}",
    Taco.class, urlVariables); */
 }

 public void updateTaco(Taco taco){
    restTemplate.put(
"http://localhost:8081/api/tacos/{orderId}",
      taco, taco.getId() );
    //orderId
 }

  public void deleteTaco(Taco taco){
    System.out.println("...Deleting taco with tacoId: "+taco.getId()+" ...");
    restTemplate.delete("http://localhost:8081/api/tacos/{id}", taco.getId());
  }

  public Taco createTaco(Taco taco){
   System.out.println("... Creating Taco...");
   return restTemplate.postForObject(
    "http://localhost:8081/api/tacos",
      taco, Taco.class);
  }


}
