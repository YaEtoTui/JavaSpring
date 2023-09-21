package com.example.mod1.task1;

public class Person {
    public Pet pet;
    public String surname;
    public int age;

    public Person(Pet pet) {
        System.out.println("Создан bean Человек");
        this.pet = pet;
    }

    public void setPet(Pet pet) {
        System.out.println("Получен Pet");
        this.pet = pet;
    }

    public void sayHello() {
        System.out.println("Привет!");
        pet.say();
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
