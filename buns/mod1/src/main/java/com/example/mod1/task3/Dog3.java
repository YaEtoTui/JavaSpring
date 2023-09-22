package com.example.mod1.task3;

import org.springframework.stereotype.Component;

@Component
public class Dog3 implements Pet3 {

    public Dog3() {
        System.out.println("Создан бин Dog task3");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав task3");
    }
}
