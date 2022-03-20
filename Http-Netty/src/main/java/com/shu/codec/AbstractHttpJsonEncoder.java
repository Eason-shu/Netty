package com.shu.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:04
 * @Description 抽象 Http Json 编码器
 **/
@Slf4j
public abstract class AbstractHttpJsonEncoder<T>  extends MessageToMessageEncoder<T> {

    final static Charset UTF_8 = StandardCharsets.UTF_8;
    //编码
    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) {
        log.info("开始编码：把消息转换成成ByteBuf");
        //将消息转换为JSON
        String jsonStr = JSON.toJSONString(body);
        // 转换成ByteBuf
        return  Unpooled.copiedBuffer(jsonStr, UTF_8);
    }

}
