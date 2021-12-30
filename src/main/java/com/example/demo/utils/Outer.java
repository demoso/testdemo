package com.example.demo.utils;

public class Outer {
    int x = 9;
    class Inner{
        int x = 8;
        public void test(){
            int x = 7;
            System.out.println(x);
            System.out.println(this.x);
            System.out.println(Outer.this.x);
            test1();
        }
    }

    private void test1(){
        System.out.println("test");
    }
    public static void main(String[] args) {
        int size=109;
        int num=4;
        int length=size/num;
        System.out.println("length:"+length);
        for(int i=0;i<num;i++){
            int start=i*length;
            int end=start+length;
            if(i+1==num){
                end=size;
            }
            System.out.println("i:"+i);
            System.out.println("start:"+start);
            System.out.println("end:"+end);
        }

        Inner in = new Outer().new Inner();
        in.test();
    }
}
