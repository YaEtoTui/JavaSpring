package com.example.mod1.task3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yaml")
public class Person {
    @Autowired
    public Pet pet;
    @Value("${surname}")
    public String surname;
    @Value("${age}")
    public int age;

    public Person(Pet pet) {
        System.out.println("Создан bean Человек task3");
        this.pet = pet;
    }

    public void sayHello() {
        System.out.printf("Привет! %s , у тебя возраст %s\n", surname, age);
        pet.say();
    }

}
