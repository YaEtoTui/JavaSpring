package com.example.mod1.task4;

public class Dog4 implements Pet4 {

    public Dog4() {
        System.out.println("Создан бин Dog task4");
    }

    @Override
    public void say() {
        System.out.println("Гав-гав task4");
    }
}
