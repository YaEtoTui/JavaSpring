package com.example.mod1.task2;

import org.springframework.stereotype.Component;

@Component("dogBean2")
public class Dog2 implements Pet2 {

    public Dog2() {
        System.out.println("Создан бин Dog task2");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав task2");
    }
}
