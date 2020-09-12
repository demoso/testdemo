//package com.example.demo.beanfactory;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//
//import java.io.IOException;
//
//@SpringBootApplication
//public class App {
//
//    public static void main(String[] args) {
//        SpringApplication.run(App.class, args);
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    @Bean
//    SpecialBeanForEngine specialBeanForEngine(){
//        return new SpecialBeanForEngine();
//    }
//
//    @Bean(initMethod="start")
//    BenzCar benzCar(Engine engine){
//        BenzCar car = new BenzCar();
//        car.engine = engine;
//        return car ;
//    }
//}
