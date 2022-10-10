package com.turkcell.rentacar;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RentacarApplication {

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(RentacarApplication.class, args);

  }

  @Bean
  public ModelMapper getModelMapper() {
    return new ModelMapper();
  }
}
