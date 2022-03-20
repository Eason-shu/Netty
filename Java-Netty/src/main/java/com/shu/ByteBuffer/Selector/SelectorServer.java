package com.shu.ByteBuffer.Selector;

import com.shu.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @Author shu
 * @Date: 2022/02/20/ 11:46
 * @Description
 **/
public class SelectorServer {
    public static void main(String[] args) throws IOException {
        // 多路分发管理器
        Selector selector = Selector.open();
        // 创建通道
        ServerSocketChannel socketChannel=ServerSocketChannel.open();
        // 绑定端口
        socketChannel.bind(new InetSocketAddress(8088));
        // 非阻塞模式
        socketChannel.configureBlocking(false);
        // selector与channel产生联系
        SelectionKey selectionKey = socketChannel.register(selector, 0, null);
        // 关注连接事件
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true){
            // 检查是否有事件发生，没有，阻塞状态
            selector.select();
            // 遍历处理的事件
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                // 处理key
                SelectionKey key = keyIterator.next();
                // 需要删除Key
                keyIterator.remove();
                System.out.println("key===>"+key);
                // 区分事件类型
                if(key.isAcceptable()){
                    ServerSocketChannel channel =(ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey register = sc.register(selector, 0, null);
                    register.interestOps(SelectionKey.OP_READ);
                    System.out.println("xxxxx===>"+sc);
                }else if(key.isReadable()){
                    SocketChannel channel =(SocketChannel) key.channel();
                    ByteBuffer buffer=ByteBuffer.allocate(16);
                    int read = channel.read(buffer);
                    if(read==-1){
                    buffer.flip();
                    }
                    else {
                        // 客户端断开,取消key
                        key.cancel();
                    }
                    ByteBufferUtil.debugAll(buffer);
                    System.out.println("yyyyy===>"+channel);
                }



            }
        }

    }
}
