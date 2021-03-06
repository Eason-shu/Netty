package com.shu.Serialize.Proto;

import com.shu.Package.PackageServer;
import com.shu.Serialize.Java.JavaServer;
import com.shu.Serialize.Pojo.SubscribeReq;
import com.shu.Serialize.Pojo.SubscribeResp;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author shu
 * @Date: 2022/03/06/ 10:18
 * @Description
 **/
public class ProtoServer {
    static final Logger log = LoggerFactory.getLogger(ProtoServer.class);

    public void start() {
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.group(boss, worker);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) {
                    ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                    ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                    ch.pipeline().addLast(new ProtobufDecoder(SubscribeReqMessage.SubscribeReq.getDefaultInstance()));
                    ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                    ch.pipeline().addLast(new ProtobufEncoder());
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            // ?????????????????????????????????
                            log.debug("connected {}", ctx.channel());
                            super.channelActive(ctx);
                        }
                        @Override
                        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                            // ?????????????????????????????????
                            log.debug("disconnect {}", ctx.channel());
                            super.channelInactive(ctx);
                        }

                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            super.channelRead(ctx, msg);
                            // ??????
                            SubscribeReqMessage.SubscribeReq req = (SubscribeReqMessage.SubscribeReq) msg;
                            // ?????????????????????????????????????????????????????????
                            boolean equals = req.getUserName().equals("admin");
                            if(equals){
                                // ????????????
                                SubscribeRespMessage.SubscribeResp.Builder builder = SubscribeRespMessage.SubscribeResp.newBuilder();
                                builder.setSubRespId(1);
                                builder.setRespCode(200);
                                builder.setDesc("??????");
                                SubscribeRespMessage.SubscribeResp resp = builder.build();
                                // ????????????
                                ctx.writeAndFlush(resp);
                            }
                        }
                    });

                }
            });
            ChannelFuture channelFuture = serverBootstrap.bind(8080);
            log.debug("{} binding...", channelFuture.channel());
            channelFuture.sync();
            log.debug("{} bound...", channelFuture.channel());
            // ??????channel
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("server error", e);
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.debug("stopped");
        }
    }

    public static void main(String[] args) {
        new ProtoServer().start();
    }
}
