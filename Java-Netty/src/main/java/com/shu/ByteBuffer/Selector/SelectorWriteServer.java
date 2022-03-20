package com.shu.ByteBuffer.Selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @Author shu
 * @Date: 2022/02/20/ 16:23
 * @Description
 **/
public class SelectorWriteServer {
    public static void main(String[] args) throws IOException {
        Selector selector=Selector.open();
        ServerSocketChannel sc=ServerSocketChannel.open();
        sc.configureBlocking(false);
        sc.bind(new InetSocketAddress(8088));
        sc.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            selector.select();
            Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                keyIterator.remove();
                if(key.isAcceptable()){
                    try (SocketChannel accept = sc.accept()) {
                        SelectionKey register = accept.register(selector, 0, null);
                        StringBuilder stringBuilder=new StringBuilder();
                        for (int i = 0; i <60000 ; i++) {
                            stringBuilder.append(i);
                        }
                        ByteBuffer byteBuffer= Charset.defaultCharset().encode(stringBuilder.toString());
                        int write = accept.write(byteBuffer);
                        System.out.println(write);
                        if(byteBuffer.hasRemaining()){
                            register.interestOps(SelectionKey.OP_WRITE);
                        }
                    }
                }
            }
        }

    }
}
