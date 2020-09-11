package com.example.demo.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;

public class TestMethodInterceptor  {

    public static void main(String[] args) {
        String a="dfdf";
        String b= new String(a.getBytes());
        if(a==b){
            System.out.println("true");
        }
        ProxyFactory proxyFactory=new ProxyFactory();
        proxyFactory.setTarget(new TestMethodInterceptor());
        proxyFactory.addAdvice(new adviseMethodInterceptor());

        Object proxy = proxyFactory.getProxy();
        TestMethodInterceptor methodInterceptor = (TestMethodInterceptor) proxy;

        methodInterceptor.doSomeThing("通过代理工厂设置代理对象，拦截代理方法");
    }

    public static class adviseMethodInterceptor implements MethodInterceptor {
        @Override
        public Object invoke(MethodInvocation methodInvocation) throws Throwable {

            Object result=null;
            try{
                System.out.println("方法执行之前："+methodInvocation.getMethod().toString());

                result= methodInvocation.proceed();

                System.out.println("方法执行之后："+methodInvocation.getMethod().toString());
                System.out.println("方法正常运行结果："+result);

                return result;

            }catch (Exception e){
                System.out.println("方法出现异常:"+e.toString());
                System.out.println("方法运行Exception结果："+result);
                return result;
            }

        }
    }

    public String doSomeThing(String someThing){

        //int i=5/0;
        return "执行被拦截的方法："+someThing;
    }
}