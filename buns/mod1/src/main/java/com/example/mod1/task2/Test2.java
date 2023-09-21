package com.example.mod1.task2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

        Person person = context.getBean("beanPerson", Person.class);
        person.sayHello();

        context.close();
    }
}
