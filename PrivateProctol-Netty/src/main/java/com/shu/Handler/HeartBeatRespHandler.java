package com.shu.Handler;

import com.shu.Pojo.Head;
import com.shu.Pojo.MessageType;
import com.shu.Pojo.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/19/ 20:42
 * @Description 心跳响应
 **/
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {

    private static final Log LOG = LogFactory.getLog(HeartBeatRespHandler.class);


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 返回心跳应答消息
        if (message.getHeader() != null
                && message.getHeader().getType() == MessageType.HEARTBEAT_REQUEST.getType()) {
            LOG.info("Receive client heart beat message : ---> "
                    + message);
            NettyMessage heartBeat = buildHeatBeat();
            LOG.info("Send heart beat response message to client : ---> "
                    + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else
            ctx.fireChannelRead(msg);
    }

    /**
     * 返回心跳响应
     * @return
     */
    private NettyMessage buildHeatBeat() {
        NettyMessage message = new NettyMessage();
        Head header = new Head();
        header.setType(MessageType.HEARTBEAT_REQUEST.getType().byteValue());
        message.setHeader(header);
        return message;
    }

}
