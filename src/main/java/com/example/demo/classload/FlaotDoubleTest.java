package com.example.demo.classload;

import java.math.BigDecimal;

public class FlaotDoubleTest {
    public static void main(String[] args)
    {

        float a=1.2f;
        float b=2.0f;
        double c=1.2d;
        double d=2.9d;
        double f=15.0/3.0;
        System.out.println(a+b);
        System.out.println(c+d);
        System.out.println(f);
        System.out.println(0.2 + 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);

        BigDecimal bigDecimal = new BigDecimal(2);
        //参数类型为double的构造方法的结果有一定的不可预知性
        BigDecimal bDouble = new BigDecimal(2.3);
        BigDecimal bString = new BigDecimal("2.3");
        System.out.println("bigDecimal=--------->1" );
        System.out.println("bigDecimal=" + bigDecimal);
        System.out.println("bDouble=" + bDouble);
        System.out.println("bString=" + bString);
        System.out.println("bigDecimal=--------->2" );
        //当double必须用作BigDecimal的源时，请使用Double.toString(double)转成String
        BigDecimal bDouble1 = BigDecimal.valueOf(2.3);
        BigDecimal bDouble2 = new BigDecimal(Double.toString(2.3));
        System.out.println("bDouble1=" + bDouble1);
        System.out.println("bDouble2=" + bDouble2);
    }
}
