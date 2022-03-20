package com.shu.ByteBuffer.NonBlocking;

import com.shu.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author shu
 * @Date: 2022/02/19/ 15:18
 * @Description 单线程Nio
 **/
public class Server {
    public static void main(String[] args) throws IOException {
        // 全局ByteBuffer
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        // 创建通道
        ServerSocketChannel socketChannel=ServerSocketChannel.open();
        // 绑定端口
        socketChannel.bind(new InetSocketAddress(8088));
        // 非阻塞模式
        socketChannel.configureBlocking(false);
        // 客服端的连接集合
        List<SocketChannel> socketList=new ArrayList<>();
        // 接受客服端案件
        while (true){
            // 客服端连接
            SocketChannel channel = socketChannel.accept();
            // 判断是否真正连接
            if(!Objects.isNull(channel)){
                channel.configureBlocking(false);
                // 添加到集合
                socketList.add(channel);
            }
            // 遍历集合
            for (SocketChannel soc : socketList) {
                // 从通道中读取数据
                int read = soc.read(buffer);

                if(read>0) {
                    // 切换写
                    buffer.flip();
                    ByteBufferUtil.debugAll(buffer);
                    // 刷新
                    buffer.clear();
                }
            }
        }
        }
    }

