package com.examples.springexsecurity.springinaction.tacos.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.examples.springexsecurity.springinaction.tacos.data.User;

public interface UserRepository 
          extends CrudRepository<User, Long> {

    User findByUsername(String username);

  //  List<User> findByUsername();

}
