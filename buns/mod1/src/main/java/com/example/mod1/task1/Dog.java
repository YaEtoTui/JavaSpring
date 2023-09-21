package com.example.mod1.task1;

public class Dog implements Pet {

    public Dog() {
        System.out.println("Создан бин Dog");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав");
    }
}
