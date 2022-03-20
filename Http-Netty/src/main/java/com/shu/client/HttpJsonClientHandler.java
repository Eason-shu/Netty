package com.shu.client;

import com.shu.codec.HttpJsonRequest;
import com.shu.pojo.Address;
import com.shu.pojo.Customer;
import com.shu.pojo.Order;
import com.shu.pojo.Shipping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:23
 * @Description 自定义处理器
 **/
public class HttpJsonClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 连接刚建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接上服务器...");
        // 向服务器发送数据
        HttpJsonRequest request = new HttpJsonRequest(null, new Order(2, new Customer(1,"ADMIN","123456"),new Address("四川","四川","四川","四川","四川"), Shipping.A,125f));
        // 写入数据
        ctx.writeAndFlush(request);
    }

    /**
     * 读取服务器返回的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.getClass().getName());
        System.out.println("接收到了数据..." + msg);
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
