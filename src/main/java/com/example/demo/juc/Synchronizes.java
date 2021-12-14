package com.example.demo.juc;



import com.example.demo.entity.BasePo;
import com.example.demo.entity.TestRole;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;



/**
 * Thrift 配置属性
 * @author yuyuan
 */
@Slf4j
public class Synchronizes
{
    private  volatile static BasePo po;
    private  volatile static TestRole role;

    public static BasePo basePo() throws InterruptedException {
        if (po == null) {
            synchronized (BasePo.class) {
                if (po == null) {
                    log.info(" 初始化BasePo");
                    Thread.sleep(5000);
                    po =new BasePo();
                }
            }
        }
        return po;
    }

    public static TestRole  testRole() throws InterruptedException {
        if (role == null) {
            synchronized (TestRole.class) {
                if (role == null) {
                    log.info(" 初始化TestRole");
                    Thread.sleep(5000);
                    role = new TestRole();
                }
            }
        }
        return role;
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                Synchronizes.basePo();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Synchronizes.basePo();
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Synchronizes.testRole();
            }
        }).start();
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Synchronizes.testRole();
            }
        }).start();
    }


}
