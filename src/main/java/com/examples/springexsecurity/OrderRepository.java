package com.examples.springexsecurity.springinaction.tacos.data;

import org.springframework.data.repository.CrudRepository;

import com.examples.springexsecurity.springinaction.tacos.TacoOrder;

public interface OrderRepository 
         extends CrudRepository<TacoOrder, Long> {

}
