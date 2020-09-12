package com.example.demo.lamda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class SupplierTest {
    //Supplier<T> 供给型接口 :
    @Test
    public void test2(){
        List<Integer> numList = getNumList(10, () -> (int)(Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }
    //需求：产生指定个数的整数，并放入集合中
    public List<Integer> getNumList(int num, Supplier<Integer> sup){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);
        }
        return list;
    }
}
