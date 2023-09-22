package com.example.mod1.task4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig4 {

    @Bean
    public Pet4 dogBean() {
        return new Dog4();
    }

    @Bean
    public Person4 personBean() {
        return new Person4(dogBean());
    }
}
