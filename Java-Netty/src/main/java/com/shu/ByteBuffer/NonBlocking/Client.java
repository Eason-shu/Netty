package com.shu.ByteBuffer.NonBlocking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @Author shu
 * @Date: 2022/02/19/ 15:32
 * @Description
 **/
public class Client {
    public static void main(String[] args) throws IOException {
        // 建立通道
        SocketChannel socketChannel=SocketChannel.open();
        // 地址
        socketChannel.connect(new InetSocketAddress("localhost",8088));
        // 写入信息
        socketChannel.write(Charset.defaultCharset().encode("hello world 哈哈哈哈"));

    }
}
