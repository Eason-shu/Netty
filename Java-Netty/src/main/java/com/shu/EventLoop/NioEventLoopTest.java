package com.shu.EventLoop;


import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;


/**
 * @Author shu
 * @Date: 2022/03/01/ 16:20
 * @Description
 **/
@Slf4j
public class NioEventLoopTest {
    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup=new NioEventLoopGroup(2);
        // 执行普通任务
        eventLoopGroup.next().submit(()->{

        }
        );

        // 执行定时任务
        eventLoopGroup.next().scheduleAtFixedRate(()->{
            System.out.println("ok");
        },0,1, TimeUnit.SECONDS);

    }
}
