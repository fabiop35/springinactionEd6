package com.examples.springexsecurity.springinaction.tacos.web;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Component
@Validated
@ConfigurationProperties(prefix="taco.orders")
public class OrderProperties {

  @Min(value=2, message="must be between 5 and 25")
  @Max(value=25, message="must be between 5 and 25")
  private int pageSize;

}
