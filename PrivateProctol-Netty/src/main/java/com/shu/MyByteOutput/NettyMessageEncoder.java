package com.shu.MyByteOutput;

import com.shu.Factory.MarshallingEncoder;
import com.shu.Pojo.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

/**
 * @Project_Name PrivateProctol-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/14/ 19:46
 * @Description netty消息编码器
 **/
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if (null == msg || null == msg.getHeader()) {
            throw new Exception("The encode message is null");
        }
        //---写入crcCode---
        sendBuf.writeInt((msg.getHeader().getCrcCode()));
        //---写入length---
        sendBuf.writeInt((msg.getHeader().getLength()));
        //---写入sessionId---
        sendBuf.writeLong((msg.getHeader().getSessionID()));
        //---写入type---
        sendBuf.writeByte((msg.getHeader().getType()));
        //---写入priority---
        sendBuf.writeByte((msg.getHeader().getPriority()));
        //---写入附件大小---
        sendBuf.writeInt((msg.getHeader().getAttachment().size()));

        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : msg.getHeader().getAttachment()
                .entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            // marshallingEncoder.encode(value, sendBuf);
        }
        // for gc
        key = null;
        keyArray = null;
        value = null;

        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else
            sendBuf.writeInt(0);
        // 之前写了crcCode 4bytes，除去crcCode和length 8bytes即为更新之后的字节
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
