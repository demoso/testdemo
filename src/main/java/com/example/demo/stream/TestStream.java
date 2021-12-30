package com.example.demo.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestStream {
    protected static final List<Employee> list = Arrays.asList(
            new Employee(1, "Alex", 1000.00),
            new Employee(2, "Michael", 2000.00),
            new Employee(3, "Jack", 1500.00),
            new Employee(4, "Owen", 1500.00),
            new Employee(5, "Denny", 2000.00));

    public static void main(String[] args) {
        //根据薪酬获取员工列表
        Map<Double,List<Employee>> map = list.stream()
                .collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println("map:"+map);
        //根据薪酬获取员工数量
        Map<Double,Long> map2 = list.stream()
                .collect(Collectors.groupingBy(Employee::getSalary,Collectors.counting()));
        System.out.println("map2:"+map2);
        //根据薪酬获取员工薪酬总数
        Map<Double,Double> map3 = list.stream()
                .collect(Collectors.groupingBy(Employee::getSalary,Collectors.summingDouble(Employee::getSalary)));
        System.out.println("map3:"+map3);
    }

}

class Employee{

    private int id;
    private Double salary;
    private String name;

    public Employee(int id,String name, Double salary) {
        this.id = id;
        this.salary = salary;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
