package com.shu.Server;

import com.shu.Pojo.SubscribeReq;
import com.shu.Pojo.SubscribeResp;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @Project_Name Marshalling-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/11/ 13:59
 * @Description 自定义处理器
 **/
@ChannelHandler.Sharable
@Slf4j
public class SubReqServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 链接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("连接开始建立");
    }


    /**
     * 开始读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
        //经过解码器handler ObjectDecoder的解码，
        //SubReqServerHandler接收到的请求消息已经被自动解码为SubscribeReq对象，可以直接使用。
        SubscribeReq req = (SubscribeReq) msg;
        if ("Lilinfeng".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req : ["
                    + req.toString() + "]");
            //对订购者的用户名进行合法性校验，校验通过后打印订购请求消息，构造订购成功应答消息立即发送给客户端。
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }

    }

    private SubscribeResp resp(int subReqID) {
        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(subReqID);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succeed, 3 days later, sent to the designated address");
        return resp;
    }


}
