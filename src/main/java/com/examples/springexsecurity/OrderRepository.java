package com.examples.springexsecurity.springinaction.tacos.data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import com.examples.springexsecurity.springinaction.tacos.TacoOrder;
import com.examples.springexsecurity.springinaction.tacos.data.User;

public interface OrderRepository 
     extends CrudRepository<TacoOrder,
                                 Long> {

     List<TacoOrder> 
        findByUserOrderByPlacedAtDesc(User user, Pageable pageable);

}
