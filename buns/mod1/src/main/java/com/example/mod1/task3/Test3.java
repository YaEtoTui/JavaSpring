package com.example.mod1.task3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        //task3
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig3.class);

        Person3 person = context.getBean("person3", Person3.class);
        person.sayHello();

        context.close();
    }
}
