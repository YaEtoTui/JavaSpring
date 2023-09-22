package com.example.mod1.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

        Person2 person = context.getBean("beanPerson2", Person2.class);
        person.sayHello();

        context.close();
    }
}
