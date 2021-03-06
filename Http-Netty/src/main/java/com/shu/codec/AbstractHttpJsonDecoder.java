package com.shu.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:15
 * @Description 抽象 Http Json 解码器
 **/
public abstract class AbstractHttpJsonDecoder<T> extends MessageToMessageDecoder<T> {

    private final Class<?> clazz;
    private final boolean isPrint;
    private final static Charset UTF_8 = StandardCharsets.UTF_8;


    protected AbstractHttpJsonDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    protected AbstractHttpJsonDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    // 解码
    protected Object decode0(ChannelHandlerContext ctx, ByteBuf body) {
        String content = body.toString(UTF_8);

        if (isPrint)
            System.out.println("The body is : " + content);

        Object result = JSON.parseObject(content, clazz);
        return result;
    }


}