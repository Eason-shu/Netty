package com.shu.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;

import java.util.List;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import static io.netty.handler.codec.stomp.StompHeaders.CONTENT_TYPE;

/**
 * @Author shu
 * @Version 1.0
 * @Date: 2022/03/07/ 21:18
 * @Description Http Json 响应编码器 编码成为HTTP响应报文 状态行、消息报头、空行和响应正文。
 **/
public class HttpJsonResponseEncoder extends AbstractHttpJsonEncoder<HttpJsonResponse> {
    @Override
    protected void encode(ChannelHandlerContext ctx, HttpJsonResponse msg, List<Object> out) throws Exception {
        //编码
        ByteBuf body = encode0(ctx, msg.getResult());
        FullHttpResponse response = msg.getHttpResponse();
        if (response == null) {
            response = new DefaultFullHttpResponse(HTTP_1_1, OK, body);
        } else {
            response = new DefaultFullHttpResponse(msg.getHttpResponse()
                    .protocolVersion(), msg.getHttpResponse().status(),
                    body);
        }
        response.headers().set(CONTENT_TYPE, "text/json");
        HttpUtil.setContentLength(response, body.readableBytes());
        out.add(response);
    }


}
