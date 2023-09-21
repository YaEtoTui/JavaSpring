package com.example.mod1.task4;

public class Person {
    private Pet pet;

    public Person(Pet pet) {
        System.out.println("Создан bean Человек task4");
        this.pet = pet;
    }

    public void sayHello() {
        System.out.println("Привет!");
        pet.say();
    }

}
