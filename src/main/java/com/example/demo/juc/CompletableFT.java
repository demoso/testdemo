package com.example.demo.juc;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @author jimmy
 */
public class CompletableFT {

    public static void main(String[] args) {


        /**
         * CompletableFuture可以从全局的 ForkJoinPool.commonPool()获得一个线程中执行这些任务
         */

        CompletableFuture<String> completableFuture=CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                return "WORLD";
            }
        });
        try{//阻塞
            String s = completableFuture.get();
            System.out.println("s:"+s);
        }catch (InterruptedException |ExecutionException e){
            e.printStackTrace();
        }
        /**
         * 自定义线程池
         */

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("s:自定义线程池");
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executorService);

        /**
         * 无需返回
         */

        CompletableFuture.runAsync(() -> {
            System.out.println("Hello");
        });

        try{
            //会阻塞
            String s = future2.get();
            System.out.println("s:"+s);
            executorService.shutdown();
        }catch (InterruptedException |ExecutionException e){
            e.printStackTrace();
        }

        /**
         * CompletableFuture.get()方法是阻塞的。它会一直等到Future完成并且在完成后返回结果。
         * 对于构建异步系统，我们应该附上一个回调给CompletableFuture，当Future完成的时候，自动的获取结果。
         * 需要等待Future完成执行的逻辑写入到回调函数中。
         * 可以使用 thenApply(), thenAccept() 和thenRun()方法附上一个回调给CompletableFuture
         */
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

         // Attach a callback to the Future using thenApply() thenApplyAsync
        /**
         * 使用thenApplyAsync()回调，将从ForkJoinPool.commonPool()获取不同的线程执行。
         * 如果你传入一个Executor到thenApplyAsync()回调中，，任务将从Executor线程池获取一个线程执行。
         */
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApplyAsync(name -> {
            return "Hello " + name;
        });

// Block and get the result of the future.
        try{
            //会阻塞
            String s = greetingFuture.get();
            System.out.println("s:"+s);
            executorService.shutdown();
        }catch (InterruptedException |ExecutionException e){
            e.printStackTrace();
        }

        /**
         * 如果你不想从你的回调函数中返回任何东西，仅仅想在Future完成后运行一些代码片段，
         * 你可以使用thenAccept() 和 thenRun()方法，这些方法经常在调用链的最末端的最后一个回调函数中使用。
         * thenRun()不能访Future的结果，它持有一个Runnable返回CompletableFuture<Void>：
         */
// thenAccept() example
        CompletableFuture.supplyAsync(() -> {
            return "str999";
        }).thenAccept(str -> {
            System.out.println("Got product detail from remote service " + str);
        }).thenRun(()-> System.out.println("THENRUN "));


        /**
         *使用thenCombine()组合两个独立的 future
         * 虽然thenCompose()被用于当一个future依赖另外一个future的时候用来组合两个future。
         * thenCombine()被用来当两个独立的Future都完成的时候，用来做一些事情。
         */
        System.out.println("Retrieving weight.");
        CompletableFuture<Double> weightInKgFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 65.0;
        });

        System.out.println("Retrieving height.");
        CompletableFuture<Double> heightInCmFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return 177.8;
        });

        System.out.println("Calculating BMI.");
        CompletableFuture<Double> combinedFuture = weightInKgFuture
                .thenCombine(heightInCmFuture, (weightInKg, heightInCm) -> {
                    Double heightInMeter = heightInCm/100;
                    return weightInKg/(heightInMeter*heightInMeter);
                });
        try {
            System.out.println("Your BMI is - " + combinedFuture.get());
        }catch (InterruptedException |ExecutionException e){
            e.printStackTrace();
        }



        /**
         * 组合多个CompletableFuture
         * CompletableFuture.allOf的使用场景是当你一个列表的独立future，并且你想在它们都完成后并行的做一些事情。
         * CompletableFuture.anyOf()和其名字介绍的一样，当任何一个CompletableFuture完成的时候【相同的结果类型】，
         * 返回一个新的CompletableFuture。
         */

        CompletableFuture<String> future5 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future6 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future7 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future5, future6, future7);

        try {
            System.out.println("anyOfFuture is - " + anyOfFuture.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }





}
