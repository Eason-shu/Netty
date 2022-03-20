package com.shu.Channel;

import java.util.concurrent.*;

/**
 * @Author shu
 * @Date: 2022/03/02/ 14:01
 * @Description
 **/
public class JdkFutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        // 提交任务
        Future<Integer> future = executorService.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 2;
            }
        });

        Integer integer = future.get();

        System.out.println(integer);
    }
}
