package com.examples.springexsecurity.springinaction.entities;

import javax.persistence.*;
import lombok.Data;

import com.examples.springexsecurity.springinaction.tacos.data.User;

@Data
@Entity
public class Authority {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;
 private String name;

 @JoinColumn(name = "user")
 @ManyToOne
 private User user;


}


