package com.example.demo.authrize.annotation;

import com.example.demo.condition.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeanConfig {
    @Bean
    public Person p() {
     Person person= new Person();
     person.setAge(11);
     person.setName("yyy");
        return person;
    }
}
