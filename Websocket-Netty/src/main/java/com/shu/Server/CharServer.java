package com.shu.Server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import javax.annotation.PreDestroy;


/**
 * @Author shu
 * @Date: 2022/03/03/ 20:05
 * @Description 服务端启动
 **/
@Component
public class CharServer {
    private static final Logger logger = LoggerFactory.getLogger(CharServer.class);
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    EventLoopGroup boss = new NioEventLoopGroup();
    EventLoopGroup work = new NioEventLoopGroup();
    ChannelFuture future = null;


    @PreDestroy
    public void stop(){
        if(future!=null){
            future.channel().close().addListener(ChannelFutureListener.CLOSE);
            future.awaitUninterruptibly();
            boss.shutdownGracefully();
            work.shutdownGracefully();
            future=null;
            logger.info(" 服务关闭 ");
        }
    }


    public void start(){
        logger.info("nettyServer 正在启动");
        int port = 8002;
        serverBootstrap.group(boss,work)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG,1024)
                .childHandler(new ChatNettyServerInitializer());
        logger.info("netty服务器在["+port+"]端口启动监听");
        try{
            future = serverBootstrap.bind(port).sync();
            if(future.isSuccess()){
                logger.info("nettyServer 完成启动 ");
            }
            // 等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        }catch (Exception e){
            logger.info("[出现异常释放资源,{%s}]",e);
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }
}
