package com.shu.Server;

import com.shu.Handle.WebSocketHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author shu
 * @Date: 2022/03/03/ 20:27
 * @Description  初始化
 **/
public class ChatNettyServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        // 增加日志信息
        socketChannel.pipeline().addLast(new LoggingHandler(LogLevel.INFO));
        // 心跳监测
        socketChannel.pipeline().addLast(new IdleStateHandler(0,0,5, TimeUnit.MINUTES));
        // Http消息编码解码
        socketChannel.pipeline().addLast(new HttpServerCodec());
        // 消息组装
        socketChannel.pipeline().addLast(new HttpObjectAggregator(65536));
        // WebSocket通信支持
        socketChannel.pipeline().addLast(new ChunkedWriteHandler());
        // 自定义处理逻辑
        socketChannel.pipeline().addLast(new WebSocketHandler());
    }
}
