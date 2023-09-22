package com.example.mod1.task4;

public class Person4 {
    private Pet4 pet;

    public Person4(Pet4 pet) {
        System.out.println("Создан bean Человек task4");
        this.pet = pet;
    }

    public void sayHello() {
        System.out.println("Привет!");
        pet.say();
    }

}
