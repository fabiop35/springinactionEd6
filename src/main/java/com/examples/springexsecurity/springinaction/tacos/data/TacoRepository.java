package com.examples.springexsecurity.springinaction.tacos.data;

//import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.examples.springexsecurity.springinaction.tacos.Taco;

public interface TacoRepository 
     extends PagingAndSortingRepository
     <Taco, Long> {

}
