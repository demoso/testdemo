package com.example.demo.aop;

public class Man  implements Person{

    @Override
    public String eat(String someThing) {
        System.out.println("执行代理方法-->吃："+someThing);
        return someThing;
    }
}