package com.shu.Client;

import com.shu.Pojo.SubscribeReq;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Project_Name Marshalling-Netty
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/11/ 14:09
 * @Description 自定义处理器
 **/
public class SubReqClientHandler extends ChannelInboundHandlerAdapter {

    public SubReqClientHandler() {
    }

    /**
     * 连接建立时
     * @param ctx
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //在链路激活的时候循环构造10条订购请求消息，最后一次性地发送给服务端。
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReq subReq(int i) {
        SubscribeReq req = new SubscribeReq();
        req.setAddress("南京市江宁区方山国家地质公园");
        req.setPhoneNumber("138xxxxxxxxx");
        req.setProductName("Netty For Marshalling");
        req.setSubReqID(i);
        req.setUserName("Lilinfeng");
        return req;
    }

    /**
     * 收到服务端返回的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        //由于对象解码器已经对订购请求应答消息进行了自动解码，
        //因此，SubReqClientHandler接收到的消息已经是解码成功后的订购应答消息。
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
