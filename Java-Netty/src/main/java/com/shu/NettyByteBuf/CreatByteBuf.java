package com.shu.NettyByteBuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.nio.ByteBuffer;

/**
 * @Author shu
 * @Date: 2022/03/02/ 15:53
 * @Description
 **/
public class CreatByteBuf {
    public static void main(String[] args) {
        // 堆内存分配
        ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer();
        // 直接内存分配
        ByteBuf directBuffer = ByteBufAllocator.DEFAULT.directBuffer();
    }
}
