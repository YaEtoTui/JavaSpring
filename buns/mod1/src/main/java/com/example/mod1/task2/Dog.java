package com.example.mod1.task2;

import org.springframework.stereotype.Component;

@Component("dogBean")
public class Dog implements Pet {

    public Dog() {
        System.out.println("Создан бин Dog task2");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав task2");
    }
}
