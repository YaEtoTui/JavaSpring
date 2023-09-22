package com.example.mod1.task4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig4.class);

        Person4 person = context.getBean("personBean", Person4.class);
        person.sayHello();

        context.close();
    }
}
