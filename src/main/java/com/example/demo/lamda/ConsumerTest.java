package com.example.demo.lamda;

//Consumer<T> 消费型接口 :

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Consumer<T> : 消费型接口
 */
public class ConsumerTest {
    @Test
    public void testq(){
        happy(10000, (m) -> System.out.println("+\':"+m+":\'+"));
        Consumer<Long> consumer = (m)-> {
            Long a= m+m;
           System.out.println(a);
        };
        consumer.accept(44l);
       // System.out.println("+\':"+hello+":\'+");
    }
    public void happy(double money, Consumer<Double> con){
        con.accept(money);
    }
}
