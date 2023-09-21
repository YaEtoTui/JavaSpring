package com.example.mod1.task4;

public class Dog implements Pet {

    public Dog() {
        System.out.println("Создан бин Dog task4");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав task4");
    }
}
