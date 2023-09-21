package com.example.mod1.task4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test4 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Person person = context.getBean("personBean", Person.class); //здесь только возьмутся первые созданные бины в task3
        person.sayHello();

        context.close();
    }
}
