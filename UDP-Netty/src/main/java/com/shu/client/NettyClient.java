package com.shu.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/08/ 16:37
 * @Description
 **/
public class NettyClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioDatagramChannel.class)
                    .handler(new MyChannelInitializer());
            Channel ch = b.bind(7398).sync().channel();
            //向目标端口发送信息
            ch.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("你好端口7397的，我是客户端小爱，你在吗！", Charset.forName("GBK")),
                    new InetSocketAddress("127.0.0.1", 7397))).sync();
            ch.closeFuture().await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

}
