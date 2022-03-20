package com.shu.Package;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @Author shu
 * @Date: 2022/03/03/ 10:29
 * @Description 粘包半包现象
 **/
public class PackageClient {
    static final Logger log = LoggerFactory.getLogger(PackageClient.class);
    public static void main(String[] args) {
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.group(worker);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    log.debug("connected...");
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            log.debug("sending...");
                            // 约定最大长度为 64
                            final int maxLength = 64;
                            // 被发送的数据
                            char c = 'a';
                            for (int i = 0; i < 10; i++) {
                                ByteBuf buffer = ctx.alloc().buffer(maxLength);
                                // 生成长度为0~62的数据
                                Random random = new Random();
                                StringBuilder sb = new StringBuilder();
                                for (int j = 0; j < (int)(random.nextInt(maxLength-2)); j++) {
                                    sb.append(c);
                                }
                                // 数据以 \n 结尾
                                sb.append("\t");
                                buffer.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));
                                c++;
                                // 将数据发送给服务器
                                ctx.writeAndFlush(buffer);
                            }
                            }

                    });
                }
            });
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 8080).sync();
            channelFuture.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            log.error("client error", e);
        } finally {
            worker.shutdownGracefully();
        }
    }
}
