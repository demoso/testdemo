package com.example.demo.beanfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Bconfig {
    @Bean
    SpecialBeanForEngine specialBeanForEngine(){
        return new SpecialBeanForEngine();
    }

    @Bean(initMethod="start")
    BenzCar benzCar(Engine engine){
        BenzCar car = new BenzCar();
        car.setEngine(engine);
        return car ;
    }
}
