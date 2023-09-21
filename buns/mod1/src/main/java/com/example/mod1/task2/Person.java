package com.example.mod1.task2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("beanPerson")
public class Person {
    @Autowired
    private Pet pet;


    public Person(@Qualifier("dogBean") Pet pet) {
        System.out.println("Создан bean Человек");
        this.pet = pet;
    }

    public void sayHello() {
        System.out.println("Привет!");
        pet.say();
    }
}
