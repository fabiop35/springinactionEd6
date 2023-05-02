package com.examples.springexsecurity.springinaction.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.examples.springexsecurity.springinaction.tacos.Taco;

public interface TacoRepository 
        extends CrudRepository<Taco, Long> {

}
