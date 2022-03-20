package com.shu.server;

import com.shu.codec.HttpJsonRequest;
import com.shu.codec.HttpJsonResponse;
import com.shu.pojo.Address;
import com.shu.pojo.Order;
import com.shu.pojo.Shipping;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.rtsp.RtspResponseStatuses.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_TYPE;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:20
 * @Description
 **/
public class HttpJsonServerHandler extends SimpleChannelInboundHandler<HttpJsonRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpJsonRequest msg) throws Exception {
        HttpRequest request = msg.getRequest();
        Order order = (Order) msg.getBody();
        System.out.println("Http server receive request : " + order);
        // 返回客服端的消息
        ChannelFuture future = ctx.writeAndFlush(new HttpJsonResponse(null, order));
        if (!HttpUtil.isKeepAlive(request)) {
            future.addListener(new GenericFutureListener<Future<? super Void>>() {
                public void operationComplete(Future future) throws Exception {
                    ctx.close();
                }
            });
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }


    private static void sendError(ChannelHandlerContext ctx,
                                  HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1,
                status, Unpooled.copiedBuffer("失败: " + status.toString()
                + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}