package com.example.mod1.task1;

public class Dog1 implements Pet1 {

    public Dog1() {
        System.out.println("Создан бин Dog");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав");
    }
}
