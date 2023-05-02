package com.examples.springexsecurity.springinactiontacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.examples.springexsecurity.springinaction.tacos.data.UserRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import com.examples.springexsecurity.springinaction.tacos.data.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  

    /** @Bean
    public PasswordEncoder encoder() {
     //return new StandardPasswordEncoder("53cr3t");
       return NoOpPasswordEncoder.getInstance();
    }**/ 

   @Autowired
   private UserDetailsService userDetailsService;
    
    @Override
    protected void configure(HttpSecurity http)
       throws Exception{

        http
         .authorizeRequests()

         //.antMatchers(HttpMethod.POST, "/design/**").permitAll()

            .antMatchers("/orders/**", "/design/**")
             .access("hasRole('USER')")
            .antMatchers("/", "/**")
             .access("permitAll")
            //.hasRole("USER")
            //.access("permitAll")
            //.anyRequest().permitAll()

        .and()
        .formLogin()
          .loginPage("/login")
          
        .and()
          .logout().logoutSuccessUrl("/")

        .and()
         .csrf()
          .ignoringAntMatchers("/h2-console/**")
         //Allow frames same origin
         .and()
          .headers()
           .frameOptions()
            .sameOrigin()


        ;
        //http.csrf().disable();
    }

    @Bean                                               public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
    }
   
    @Override
    protected void configure(
        AuthenticationManagerBuilder auth)
                            throws Exception {
            auth.userDetailsService(
                    userDetailsService)
            .passwordEncoder(passwordEncoder());
    } 





   @Bean
    public UserDetailsService 
     userDetailsService(UserRepository userRepo) {

       System.out.println("》》》SecurityConfig.userDetailsService 《《《");  

       return username -> { 
        User user = userRepo.findByUsername(username);
        if (user != null) 
        return user;

        throw new UsernameNotFoundException("User '" + username + "' not found");

        };
    }



  /**  @Bean
    public SecurityFilterChain 
            filterChain(HttpSecurity http)
             throws Exception {

        System.out.println("》》》SecurityComfig.filterChain 《《《");     
        
        return http
           .authorizeRequests()
           //.antMatchers("/design", "/orders").access("hasRole('USER')")
            //.antMatchers("/design", "/orders")
             //.hasRole("USER").anyRequest()
             //.permitAll()
           
            //.and()
            //  .authorizeRequests() 
            .mvcMatchers(HttpMethod.POST)
            .access("hasRole('USER')")
            .anyRequest()
            //.hasRole("USER").anyRequest()
            .permitAll()
            //.and()
            // .authorizeRequests()
            
            .and()
              .formLogin()
               .loginPage("/login")
            
            .and()
               .logout()
                .logoutSuccessUrl("/")

           .and()
            .build();
            //http.csrf().disable();
    } **/

}
