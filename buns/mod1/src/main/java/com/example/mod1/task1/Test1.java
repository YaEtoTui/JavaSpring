package com.example.mod1.task1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Person1 person = context.getBean("personBean", Person1.class);
        person.sayHello();

        context.close();
    }
}
