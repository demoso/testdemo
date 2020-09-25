package com.example.demo.condition;

import lombok.Builder;

@Builder
public class Person {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Person() {
    }
    public boolean hasRole(String name) {
        System.out.println("-->p.hasRole");
        if(name.equals("Admin")){
            return true;
        }else {
            return false;
        }

    }
    @Override
    public String toString() {
        return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
    }
}
