package com.shu.Channel;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Author shu
 * @Date: 2022/03/02/ 14:26
 * @Description
 **/
public class NettyPromiseTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        EventLoopGroup eventLoopGroup=new NioEventLoopGroup();

        // 可以把他理解为结果集
        DefaultPromise<Integer> promise = new DefaultPromise<Integer>(eventLoopGroup.next());

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 自定义线程向Promise中存放结果
            promise.setSuccess(50);
        }).start();

        // 主线程从Promise中获取结果
        System.out.println(Thread.currentThread().getName() + " " + promise.get());
    }
}
